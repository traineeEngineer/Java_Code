package com.GateWay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CloudGateWayApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudGateWayApplication.class, args);
	}

}
