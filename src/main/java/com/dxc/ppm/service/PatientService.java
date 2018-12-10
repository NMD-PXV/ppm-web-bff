package com.dxc.ppm.service;

import com.dxc.ppm.api.DoctorApi;
import com.dxc.ppm.api.NurseApi;
import com.dxc.ppm.api.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PatientService {
    @Autowired
    private NurseApi nurseApi;

    @Autowired
    private DoctorApi doctorApi;

    public String upsert(Patient patient) {
        String patientId = nurseApi.upsert(patient.getPersonalInfo());
        doctorApi.upsertProfiles(patientId, patient.getMedicalTreatmentProfile());
        return patientId;
    }

    public Patient readPatientById(String patientId) {
        Patient patient = new Patient();
        patient.setId(patientId);
        patient.setPersonalInfo(nurseApi.readPatientInfoById(patientId));
        patient.setMedicalTreatmentProfile(doctorApi.searchProfilesByPatientId(patientId));
        return patient;
    }

    public List<Patient> searchPatients(String name, String disease, String medicine) {
        List<String> patientIds = nurseApi.searchPatientIdsByName(name);
        doctorApi.searchTreatmentProfiles(patientIds, disease, medicine);

        return null;
    }
}
