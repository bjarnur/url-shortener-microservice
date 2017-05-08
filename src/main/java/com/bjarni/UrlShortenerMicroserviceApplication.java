package com.bjarni;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class UrlShortenerMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrlShortenerMicroserviceApplication.class, args);
	}
}
