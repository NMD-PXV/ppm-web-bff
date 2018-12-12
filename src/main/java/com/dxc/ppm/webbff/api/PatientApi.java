package com.dxc.ppm.webbff.api;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "PatientApi", url = "${PatientApi.url}")
public interface PatientApi {
    @PostMapping("/v1/profiles")
    List<String> getIsNotDeletedIds(@RequestBody List<String> ids);

    @PostMapping("v1/patients")
    List<String> addPatients(@RequestBody List<String> patientIds);

    @DeleteMapping("v1/patients")
    String deletePatientProfiles(@RequestBody List<String> ids);
}
