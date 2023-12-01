package com.agrisafe.famerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EntityScan(basePackages = {"com.agrisafe.common.model"})
public class FarmerService {
    public static void main(String[] args) {
        SpringApplication.run(FarmerService.class, args);
    }
}
