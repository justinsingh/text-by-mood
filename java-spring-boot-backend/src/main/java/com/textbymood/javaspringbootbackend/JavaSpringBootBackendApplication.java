package com.textbymood.javaspringbootbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class JavaSpringBootBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaSpringBootBackendApplication.class, args);
	}
}