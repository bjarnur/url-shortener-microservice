package com.bjarni;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Goal is to create a project that emits shortened URLs for
 * any URL that is passed into it. The project should also 
 * support for users visit any URLs vis shortened URLs that have
 * been created. 
 * 
 * Solution is built as a microservice to optimize for high
 * availability, and to make it possible to scale beyond one
 * machine.
 * 
 * The solution is still pretty rough, for example it's currently 
 * storing all information in memory. A good opportunity for 
 * improvement would be to implement connection to a datasource that  
 * could be shared between all instances of the service. 
 * 
 * @author bjarni
 */
@EnableDiscoveryClient
@SpringBootApplication
public class UrlShortenerMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrlShortenerMicroserviceApplication.class, args);
	}
}
