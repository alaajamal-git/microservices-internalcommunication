package com.mobileapp.posts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MobileAppPostsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MobileAppPostsApplication.class, args);
	}
	
	

}
