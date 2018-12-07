package com.dxc.ppm.api;

import com.dxc.ppm.api.model.MedicalTreatmentProfile;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@FeignClient(name = "DoctorApi", url = "${DoctorApi.url}")
public interface DoctorApi {

    @PostMapping("/v1/patients/{id}/profiles")
    String upsertProfiles(@PathVariable("id") String patientId,
                          @RequestBody List<MedicalTreatmentProfile> profiles);

    @GetMapping("/v1/patients")
    List<MedicalTreatmentProfile> searchTreatmentProfiles(@RequestParam("name") String patientName,
                                                 @RequestParam("disease") String disease,
                                                 @RequestParam("medicine") String medicineName);

    @GetMapping("/v1/patients/{id}/profiles/tests")
    String checkAllergic(@RequestParam("name") String medicineName);

    @GetMapping("/v1/patients/{id}/profiles")
    List<MedicalTreatmentProfile> searchProfilesByPatientId(@PathVariable("id") String patientId);


}
