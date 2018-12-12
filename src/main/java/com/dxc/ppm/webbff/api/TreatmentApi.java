package com.dxc.ppm.webbff.api;

import com.dxc.ppm.webbff.api.model.MedicalTreatmentProfile;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "TreatmentApi", url = "${TreatmentApi.url}")
public interface TreatmentApi {

    @PostMapping("/v1/patients/{id}/profiles")
    String upsertProfiles(@PathVariable("id") String patientId,
                          @RequestBody List<MedicalTreatmentProfile> profiles);

    @GetMapping("/v1/patients")
    List<MedicalTreatmentProfile> searchTreatmentProfiles(@RequestParam("ids") List<String> patientIds,
                                                 @RequestParam("disease") String disease,
                                                 @RequestParam("medicine") String medicineName);

    @GetMapping("/v1/patients/{id}/profiles/tests")
    String checkAllergic(@PathVariable("id") String patientId, @RequestParam("name") String medicineName);

    @GetMapping("/v1/patients/{id}/profiles")
    List<MedicalTreatmentProfile> searchProfilesByPatientId(@PathVariable("id") String patientId);
}
