package com.dxc.ppm.webbff.api;

import com.dxc.ppm.webbff.api.model.PersonalInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "InfoApi", url = "${InfoApi.url}")
public interface InfoApi {
    @PostMapping("/v1/patients/info")
    String upsert(@RequestBody PersonalInfo info);

    @GetMapping("/v1/patients/{patientId}/info")
    PersonalInfo readPatientInfoById(@PathVariable("patientId") String patientId);

    @GetMapping("/v1/patients/info/names")
    List<PersonalInfo> searchPatientsByName(@RequestParam("patientName") String patientName);

    @PostMapping("/v1/patients/info/bulk")
    List<PersonalInfo> readMultiPatientInfoById(@RequestBody List<String> ids);
}
