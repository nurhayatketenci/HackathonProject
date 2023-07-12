package com.study.expensetracking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling

public class ExpenseTrackingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpenseTrackingApplication.class, args);
	}

}
