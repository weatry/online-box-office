package com.github.budwing.obo.seata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "io.seata" })
public class SeataServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(io.seata.server.ServerApplication.class, args);
    }
}
