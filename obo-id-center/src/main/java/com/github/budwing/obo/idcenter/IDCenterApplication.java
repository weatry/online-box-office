package com.github.budwing.obo.idcenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class IDCenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(IDCenterApplication.class, args);
    }
}
