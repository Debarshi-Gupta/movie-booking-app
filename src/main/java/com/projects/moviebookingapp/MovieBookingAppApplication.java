package com.projects.moviebookingapp;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
		info = @Info(
				title = "Movie Booking Application",
				description = "A Movie Booking Application with User and Admin Role",
				version = "v1.0.0",
				contact = @Contact(
						name = "Debarshi Gupta",
						email = "debarshigupta47@gmail.com"
				)
		)
)
@SpringBootApplication
public class MovieBookingAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieBookingAppApplication.class, args);
	}

}
