package com.dxc.ppm.api;

import com.dxc.ppm.api.model.PersonalInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "NurseApi", url = "${NurseApi.url}")
public interface NurseApi {
    @PostMapping("/v1/patients/info/bulk")
    String upsertMultiPatientInfos(@RequestBody List<PersonalInfo> infos);

    @GetMapping("/v1/patients/{patientId}/info")
    PersonalInfo readPatientInfoById(@PathVariable("patientId") String patientId);
}
