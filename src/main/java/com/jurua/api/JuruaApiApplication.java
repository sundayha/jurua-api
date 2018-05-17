package com.jurua.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 张博【zhangb@lianliantech.cn】
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.jurua.api")
public class JuruaApiApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(JuruaApiApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(JuruaApiApplication.class, args);
	}
}
