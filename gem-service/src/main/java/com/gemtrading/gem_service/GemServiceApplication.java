package com.gemtrading.gem_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class GemServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GemServiceApplication.class, args);
	}

}
