package com.dxc.ppm.api;

import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(name = "AdminApi", url = "${AdminApi.url}")
public interface AdminApi {
}
