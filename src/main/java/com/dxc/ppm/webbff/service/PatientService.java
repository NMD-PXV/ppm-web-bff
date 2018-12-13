package com.dxc.ppm.webbff.service;

import com.dxc.ppm.webbff.api.PatientApi;
import com.dxc.ppm.webbff.api.TreatmentApi;
import com.dxc.ppm.webbff.api.InfoApi;
import com.dxc.ppm.webbff.exception.BffException;
import com.dxc.ppm.webbff.api.model.MedicalTreatmentProfile;
import com.dxc.ppm.webbff.api.model.Patient;
import com.dxc.ppm.webbff.api.model.PersonalInfo;
import com.dxc.ppm.webbff.common.BffError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.dxc.ppm.webbff.common.BffError.*;

@Service
public class PatientService {
    @Autowired
    private InfoApi infoApi;

    @Autowired
    private TreatmentApi treatmentApi;

    @Autowired
    private PatientApi patientApi;

    public String upsert(Patient patient) {
        checkInputPatient(patient);
        String patientId = infoApi.upsert(patient.getPersonalInfo());
        treatmentApi.upsertProfiles(patientId, patient.getMedicalTreatmentProfile());
        patientApi.addPatients(Collections.singletonList(patientId));
        return patientId;
    }

    public Patient readPatientById(String patientId) {
        checkPatientId(patientId);
        if(patientApi.getIsNotDeletedIds(Collections.singletonList(patientId)).isEmpty())
            throw new BffException(PATIENT_NOT_FOUND);
        Patient patient = new Patient();
        patient.setPatientId(patientId);
        patient.setPersonalInfo(infoApi.readPatientInfoById(patientId));
        patient.setMedicalTreatmentProfile(treatmentApi.searchProfilesByPatientId(patientId));
        return patient;
    }

    public String searchTest(String patientId, String medicineName) {
        checkPatientId(patientId);
        if (medicineName.isEmpty()) throw new BffException(MEDICINE_NAME_IS_NULL);

        return treatmentApi.checkAllergic(patientId, medicineName);
    }

    public List<String> upsertMultiPatients(List<Patient> patients) {
        List<String> results = new ArrayList<>();
        for (Patient patient : patients) {
            checkInputPatient(patient);
            String patientId = infoApi.upsert(patient.getPersonalInfo());
            results.add(treatmentApi.upsertProfiles(patientId, patient.getMedicalTreatmentProfile()));
        }
        patientApi.addPatients(results);
        return results;
    }


    public void checkPatientId(String patientId) {
        if (patientId.isEmpty() || patientId.contains(" "))
            throw new BffException(PATIENT_ID_IS_NULL_OR_CONTAINS_SPACE, patientId);
    }

    public void checkInputPatient(Patient patient) {
        if (patient.getPersonalInfo().getPatientId() == null || patient.getPersonalInfo().getAddress() == null
                || patient.getPersonalInfo().getDob() == null || patient.getPersonalInfo().getSex() == null
                || patient.getPersonalInfo().getPob() == null || patient.getPersonalInfo().getFullname() == null)
            throw new BffException(INVALID_INPUT_PATIENT_INFO);
        for (MedicalTreatmentProfile medicalProfile : patient.getMedicalTreatmentProfile()) {
            if (medicalProfile.getDoctor() == null|| medicalProfile.getCreatedDate() == null)
                throw new BffException(INVALID_INPUT_PATIENT_MEDICAL_PROFILE, medicalProfile);
        }
    }

    public List<Patient> searchPatients(String name, String disease, String medicine) {
        List<PersonalInfo> infos = infoApi.searchPatientsByName(name);
        List<String> patientIds = infos.stream().map(PersonalInfo::getPatientId).collect(Collectors.toList());
        List<String> isNotDeletedIds = patientApi.getIsNotDeletedIds(patientIds);
        List<MedicalTreatmentProfile> profiles;
        if (!isNotDeletedIds.isEmpty()) {
            infos = infoApi.readMultiPatientInfoById(patientIds);
            profiles = treatmentApi.searchTreatmentProfiles(isNotDeletedIds, disease, medicine);
        } else {
            profiles = treatmentApi.searchTreatmentProfiles(isNotDeletedIds, disease, medicine);
            Set<String> ids = profiles.stream().map(MedicalTreatmentProfile::getPatientId).collect(Collectors.toSet());
            infos = infoApi.readMultiPatientInfoById(new ArrayList<>(ids));
        }

        if (infos.isEmpty())
            return new ArrayList<>();
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
            patients.stream()
                    .filter(p -> p.getPatientId().equals(e.getKey()))
                    .findAny()
                    .get()
                    .setMedicalTreatmentProfile(e.getValue());
        }
        return patients;
    }

    public String deletePatientProfiles(List<String> patientIds) {
        return patientApi.deletePatientProfiles(patientIds);
    }
}
