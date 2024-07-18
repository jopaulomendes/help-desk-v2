package com.jopaulo.orderserviceapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OrderServiceApiApplication {
    public static void main(String[] args) {
        try {
            SpringApplication.run(OrderServiceApiApplication.class, args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
