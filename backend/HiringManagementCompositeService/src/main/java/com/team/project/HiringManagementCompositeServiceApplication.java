package com.team.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
//@EnableScheduling
public class HiringManagementCompositeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(HiringManagementCompositeServiceApplication.class, args);
    }

}
