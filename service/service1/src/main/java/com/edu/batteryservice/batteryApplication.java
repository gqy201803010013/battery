package com.edu.batteryservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
@SpringBootApplication
@ComponentScan(basePackages = {"com.edu"})
@MapperScan("com.edu.batteryservice.mapper")
public class batteryApplication {
    public static void main(String[] args) {
        SpringApplication.run(batteryApplication.class, args);
    }
}
