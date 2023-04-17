package com.team.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication ( exclude = HibernateJpaAutoConfiguration.class)
@EnableEurekaClient
public class AuthenticationServiceApplication {
    public static void main(String[] args) {

        SpringApplication.run(AuthenticationServiceApplication.class, args);
    }
}
