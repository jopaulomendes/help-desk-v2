package com.jopaulo.authserviceapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthServiceApiApplication {

	public static void main(String[] args) {
		try {
			SpringApplication.run(AuthServiceApiApplication.class, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
