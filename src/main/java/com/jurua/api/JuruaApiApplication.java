package com.jurua.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.jurua.api")
public class JuruaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(JuruaApiApplication.class, args);
	}
}
