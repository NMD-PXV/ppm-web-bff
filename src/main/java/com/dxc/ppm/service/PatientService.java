package com.dxc.ppm.service;

import com.dxc.ppm.api.DoctorApi;
import com.dxc.ppm.api.NurseApi;
import com.dxc.ppm.api.model.*;
import com.dxc.ppm.exception.WebBFFException;
import io.swagger.util.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.dxc.ppm.common.WebBFFStorageError.*;

@Service
public class PatientService {
    @Autowired
    private NurseApi nurseApi;

    @Autowired
    private DoctorApi doctorApi;

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
        patient.setId(patientId);
        patient.setPersonalInfo(nurseApi.readPatientInfoById(patientId));
        patient.setMedicalTreatmentProfile(doctorApi.searchProfilesByPatientId(patientId));
        return patient;
    }

    public String searchTest(String patientId, String medicineName) {
        checkPatientId(patientId);
        if(medicineName.isEmpty()) throw new WebBFFException(MEDICINE_NAME_IS_NULL);

        return doctorApi.checkAllergic(patientId, medicineName);
    }

    public List<MedicalTreatmentProfile> searchPatients(String name, String disease, String medicine) {
      return null;
    }

    public String upsertMultiPatients (List<Patient> patients) {
        List<String> results = new ArrayList<>();
        for(Patient patient: patients) {
            checkInputPatient(patient);
            String patientId = nurseApi.upsert(patient.getPersonalInfo());
            results.add(doctorApi.upsertProfiles(patientId, patient.getMedicalTreatmentProfile()));
        }
        return results.stream().collect(Collectors.joining(", ", "[", "]"));
    }


    public void checkPatientId(String patientId) {
        if(patientId.isEmpty() || patientId.contains(" "))
            throw new WebBFFException(PATIENT_ID_IS_NULL_OR_CONTAINS_SPACE, patientId);
    }

    public void checkInputPatient(Patient patient) {
        if(patient.getPersonalInfo().getFullName().isEmpty()
                || patient.getPersonalInfo().getAddress().isEmpty()
                || patient.getPersonalInfo().getPob().isEmpty()
                || patient.getPersonalInfo().getSex().isEmpty()
                || patient.getPersonalInfo().getDob().toString().isEmpty())
            throw new WebBFFException(INVALID_INPUT_PATIENT_INFO, patient.getPersonalInfo());

        for (MedicalTreatmentProfile medicalProfile: patient.getMedicalTreatmentProfile()) {
            if(medicalProfile.getDoctor().isEmpty() || medicalProfile.getCreatedDate().toString().isEmpty())
                throw new WebBFFException(INVALID_INPUT_PATIENT_MEDICAL_PROFILE, medicalProfile);
        }
    }

}
