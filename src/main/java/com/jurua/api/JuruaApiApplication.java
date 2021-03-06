package com.jurua.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author 张博【zhangb@lianliantech.cn】
 *
 */

@SpringBootApplication(scanBasePackages = "com.jurua.api")
@EnableCaching
@EnableAsync
public class JuruaApiApplication extends SpringBootServletInitializer {

	/**
	 * 只要部署 war 包，就需要继承 SpringBootServletInitializer 这个类，并重写 configure 方法
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
