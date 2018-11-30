package com.dxc.ppm.delegate;

import com.dxc.ppm.api.V1ApiDelegate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class V1ApiDelegateImp implements V1ApiDelegate {

    @Override
    public ResponseEntity<String> readPatientById(String patientId) {
        return null;
    }

    @Override
    public ResponseEntity<String> upsertMultiPatients(String json) {
        return null;
    }

    @Override
    public ResponseEntity<String> searchPatients(String name, String disease, String medicine) {
        return null;
    }

    @Override
    public ResponseEntity<Void> searchTest(String id, String name) {
        return null;
    }
}
