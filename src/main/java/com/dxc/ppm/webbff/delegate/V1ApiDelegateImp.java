package com.dxc.ppm.webbff.delegate;

import com.dxc.ppm.webbff.api.V1ApiDelegate;
import com.dxc.ppm.webbff.api.model.Patient;
import com.dxc.ppm.webbff.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class V1ApiDelegateImp implements V1ApiDelegate {

    @Autowired
    private PatientService patientService;


    @Override
    public ResponseEntity<String> deletePatientProfiles(List<String> patientIds) {
        return ResponseEntity.ok(patientService.deletePatientProfiles(patientIds));
    }

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
