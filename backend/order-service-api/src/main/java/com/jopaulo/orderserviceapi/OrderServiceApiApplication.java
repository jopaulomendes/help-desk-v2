package com.jopaulo.orderserviceapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.time.LocalDateTime;

@EnableFeignClients
@SpringBootApplication
public class OrderServiceApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApiApplication.class, args);
    }
}
