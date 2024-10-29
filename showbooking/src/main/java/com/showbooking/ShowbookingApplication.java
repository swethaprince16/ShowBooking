package com.showbooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class ShowbookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShowbookingApplication.class, args);
	}

}
