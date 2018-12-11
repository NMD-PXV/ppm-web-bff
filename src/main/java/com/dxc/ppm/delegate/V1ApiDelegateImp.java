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
        return ResponseEntity.ok(patientService.readPatientById(patientId));
    }

    @Override
    public ResponseEntity<List<Patient>> searchPatients(String name, String disease, String medicine) {
        return ResponseEntity.ok(patientService.searchPatients(name, disease, medicine));
    }

    @Override
    public ResponseEntity<String> searchTest(String id, String name) {
        return ResponseEntity.ok(patientService.searchTest(id, name));
    }

    @Override
    public ResponseEntity<String> upsert(Patient patient) {
        return ResponseEntity.ok(patientService.upsert(patient));
    }

    @Override
    public ResponseEntity<List<String>> upsertMultiPatients(List<Patient> patients) {
        return ResponseEntity.ok(patientService.upsertMultiPatients(patients));
    }
}
