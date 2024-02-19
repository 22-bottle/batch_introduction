package com.test.companyBatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class CompanyBatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompanyBatchApplication.class, args);
	}

}
