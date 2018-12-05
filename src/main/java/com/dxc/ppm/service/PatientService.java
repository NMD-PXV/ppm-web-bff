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
}
