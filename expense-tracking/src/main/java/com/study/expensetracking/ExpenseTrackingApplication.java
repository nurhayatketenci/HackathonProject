package com.study.expensetracking;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class ExpenseTrackingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpenseTrackingApplication.class, args);
	}
	@Bean
	public OpenAPI baseOpenAPI() {
		SecurityScheme jwtScheme = new SecurityScheme()
				.type(SecurityScheme.Type.HTTP)
				.scheme("bearer")
				.bearerFormat("JWT");

		return new OpenAPI()
				.components(new Components().addSecuritySchemes("jwt", jwtScheme))
				.info(new Info().title("Expense Tracking Project")
						.version("1.0.0")
						.description("OpenAPI documentation "))
				.addSecurityItem(new SecurityRequirement().addList("jwt"));
	}

}
