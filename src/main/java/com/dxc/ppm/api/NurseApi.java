package com.dxc.ppm.api;

import com.dxc.ppm.api.model.PersonalInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "NurseApi", url = "${NurseApi.url}")
public interface NurseApi {
    @PostMapping("/v1/patients/info")
    String upsert(@RequestBody PersonalInfo info);

    @GetMapping("/v1/patients/{patientId}/info")
    PersonalInfo readPatientInfoById(@PathVariable("patientId") String patientId);

    @GetMapping("v1/patients/info/names")
    List<PersonalInfo> searchPatientsByName(@RequestParam("patientName") String patientName);
}
