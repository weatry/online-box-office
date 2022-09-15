package com.github.budwing.obo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@SpringBootApplication
public class OBOApplication {
    @RequestMapping("/")
    public String home() {
        return "This is Online Box Office home page.";
    }
    public static void main(String[] args) {
        log.debug("OBO is starting......");
        SpringApplication.run(OBOApplication.class, args);
        log.debug("OBO started.");
    }
}
