package com.example.pepsales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PepsalesNotificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(PepsalesNotificationApplication.class, args);
	}

}
