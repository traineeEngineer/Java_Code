package com.spring.demoproductservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class DemoProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoProductServiceApplication.class, args);
	}

}
