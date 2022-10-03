package com.example.securityb2tech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SecurityB2TechApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityB2TechApplication.class, args);
	}

}
