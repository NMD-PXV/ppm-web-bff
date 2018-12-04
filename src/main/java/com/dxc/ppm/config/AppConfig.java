package com.dxc.ppm.config;

import org.springframework.cloud.netflix.feign.EnableFeignClients;

@EnableFeignClients(basePackages = "com.dxc.ppm.api")
public class AppConfig {
}