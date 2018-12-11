package com.dxc.ppm.api;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "AdminApi", url = "${AdminApi.url}")
public interface AdminApi {
    @PostMapping("/v1/admin/profiles")
    List<String> getIsNotDeletedIds(@RequestBody List<String> ids);

    @PostMapping("v1/admin/patients")
    List<String> addPatients(@RequestBody List<String> patientIds);

    @DeleteMapping("v1/admin/patients")
    String deletePatientProfiles(@RequestBody List<String> ids);
}
