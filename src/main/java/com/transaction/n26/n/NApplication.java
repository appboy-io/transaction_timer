package com.transaction.n26.n;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class NApplication {
	public static void main(String[] args) {
		SpringApplication.run(NApplication.class, args);
	}
}
