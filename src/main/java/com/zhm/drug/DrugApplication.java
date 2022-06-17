package com.zhm.drug;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class DrugApplication {
    public static void main(String[] args) {
        SpringApplication.run(DrugApplication.class, args);
    }

}
