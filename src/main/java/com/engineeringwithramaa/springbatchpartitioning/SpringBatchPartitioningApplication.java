package com.engineeringwithramaa.springbatchpartitioning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringBatchPartitioningApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBatchPartitioningApplication.class, args);
	}

}
