package com.jurua.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 张博【zhangb@lianliantech.cn】
 *
 */
@SpringBootApplication
@EnableCaching
@ComponentScan(basePackages = "com.jurua.api")
public class JuruaApiApplication extends SpringBootServletInitializer {

	/**
	 * 只要部署 war 包需要 继承 SpringBootServletInitializer 这个类，并重写 configure 方法
	 * @param application spring 上下文实例
	 * @return SpringApplicationBuilder
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(JuruaApiApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(JuruaApiApplication.class, args);
	}
}
