package com.dxc.ppm.delegate;

import com.dxc.ppm.api.V1ApiDelegate;
import com.dxc.ppm.api.model.MedicalTreatmentProfile;
import com.dxc.ppm.api.model.Patient;
import com.dxc.ppm.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class V1ApiDelegateImp implements V1ApiDelegate {

    @Autowired
    private PatientService patientService;


    @Override
    public ResponseEntity<Patient> readPatientById(String patientId) {
        return null;
    }

    @Override
    public ResponseEntity<List<MedicalTreatmentProfile>> searchPatients(String name, String disease, String medicine) {
        return null;
    }

    @Override
    public ResponseEntity<List<String>> searchTest(String id, String name) {
        return null;
    }

    @Override
    public ResponseEntity<String> upsert(Patient patient) {
        return null;
    }

    @Override
    public ResponseEntity<String> upsertMultiPatients(List<Patient> patient) {
        return null;
    }
}
