package com.smartbudgetbounty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SmartBudgetBountyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartBudgetBountyApplication.class, args);
        System.out.println("Main entered.");
    }
}