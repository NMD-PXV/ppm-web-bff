package com.dxc.ppm.webbff.config;

import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.dxc.ppm.webbff")
public class AppConfig {
}