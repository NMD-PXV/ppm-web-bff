package com.dxc.ppm.service;

import com.dxc.ppm.api.AdminApi;
import com.dxc.ppm.api.DoctorApi;
import com.dxc.ppm.api.NurseApi;
import com.dxc.ppm.api.model.*;
import com.dxc.ppm.exception.BffException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.dxc.ppm.common.BffError.*;

@Service
public class PatientService {
    @Autowired
    private NurseApi nurseApi;

    @Autowired
    private DoctorApi doctorApi;

    @Autowired
    private AdminApi adminApi;

    //TODO write exception

    public String upsert(Patient patient) {
        checkInputPatient(patient);
        String patientId = nurseApi.upsert(patient.getPersonalInfo());
        doctorApi.upsertProfiles(patientId, patient.getMedicalTreatmentProfile());
        return patientId;
    }

    public Patient readPatientById(String patientId) {
        checkPatientId(patientId);
        Patient patient = new Patient();
        patient.setPatientId(patientId);
        patient.setPersonalInfo(nurseApi.readPatientInfoById(patientId));
        patient.setMedicalTreatmentProfile(doctorApi.searchProfilesByPatientId(patientId));
        return patient;
    }

    public String searchTest(String patientId, String medicineName) {
        checkPatientId(patientId);
        if (medicineName.isEmpty()) throw new BffException(MEDICINE_NAME_IS_NULL);

        return doctorApi.checkAllergic(patientId, medicineName);
    }

    public String upsertMultiPatients(List<Patient> patients) {
        List<String> results = new ArrayList<>();
        for (Patient patient : patients) {
            checkInputPatient(patient);
            String patientId = nurseApi.upsert(patient.getPersonalInfo());
            results.add(doctorApi.upsertProfiles(patientId, patient.getMedicalTreatmentProfile()));
        }
        return results.stream().collect(Collectors.joining(", ", "[", "]"));
    }


    public void checkPatientId(String patientId) {
        if (patientId.isEmpty() || patientId.contains(" "))
            throw new BffException(PATIENT_ID_IS_NULL_OR_CONTAINS_SPACE, patientId);
    }

    public void checkInputPatient(Patient patient) {
        if (patient.getPersonalInfo().getFullname().isEmpty()
                || patient.getPersonalInfo().getAddress().isEmpty()
                || patient.getPersonalInfo().getPob().isEmpty()
                || patient.getPersonalInfo().getSex().isEmpty()
                || patient.getPersonalInfo().getDob().toString().isEmpty()
        )
            throw new BffException(INVALID_INPUT_PATIENT_INFO, patient.getPersonalInfo());

        for (MedicalTreatmentProfile medicalProfile : patient.getMedicalTreatmentProfile()) {
            if (medicalProfile.getDoctor().isEmpty() || medicalProfile.getCreatedDate().toString().isEmpty())
                throw new BffException(INVALID_INPUT_PATIENT_MEDICAL_PROFILE, medicalProfile);
        }
    }

    public List<Patient> searchPatients(String name, String disease, String medicine) {
        List<PersonalInfo> infos = nurseApi.searchPatientsByName(name);
        List<String> patientIds = infos.stream().map(PersonalInfo::getPatientId).collect(Collectors.toList());
        List<String> isNotDeletedIds = adminApi.getIsNotDeletedIds(patientIds);
//        if (isNotDeletedIds.isEmpty())

//        patientIds.retainAll();
        List<MedicalTreatmentProfile> profiles = doctorApi.searchTreatmentProfiles(patientIds, disease, medicine);

        List<Patient> patients = new ArrayList<>();
        Map<String, List<MedicalTreatmentProfile>> map = new HashMap<>();
        for (PersonalInfo i : infos) {
            Patient patient = new Patient();
            patient.setPatientId(i.getPatientId());
            patient.setPersonalInfo(i);
            patients.add(patient);
        }
        for (MedicalTreatmentProfile p : profiles) {
            if (!map.containsKey(p.getPatientId()))
                map.put(p.getPatientId(), Collections.singletonList(p));
            else
                map.get(p.getPatientId()).add(p);
        }
        for (Map.Entry<String, List<MedicalTreatmentProfile>> e : map.entrySet()) {
            patients.stream().
                    filter(p -> p.getPatientId().equals(e.getKey())).
                    findAny().
                    get().
                    setMedicalTreatmentProfile(e.getValue());
        }
        return patients;
    }
}
