package com.hcr.activitimodule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {org.activiti.spring.boot.SecurityAutoConfiguration.class})
public class ActivitimoduleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActivitimoduleApplication.class, args);
    }

}
