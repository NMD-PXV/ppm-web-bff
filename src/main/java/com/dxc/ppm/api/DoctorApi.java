package com.dxc.ppm.api;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "DoctorApi", url = "${DoctorApi.url}")
public interface DoctorApi {
}
