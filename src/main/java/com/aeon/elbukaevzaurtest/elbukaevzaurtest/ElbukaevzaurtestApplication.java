package com.aeon.elbukaevzaurtest.elbukaevzaurtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class ElbukaevzaurtestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElbukaevzaurtestApplication.class, args);
	}

}
