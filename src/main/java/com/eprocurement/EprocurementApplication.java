package com.eprocurement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class EprocurementApplication {
	public static void main(String[] args) {
		SpringApplication.run(EprocurementApplication.class, args);
	}
}
