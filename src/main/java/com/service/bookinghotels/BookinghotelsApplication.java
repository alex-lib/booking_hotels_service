package com.service.bookinghotels;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.service.bookinghotels.repositories")
@EntityScan(basePackages = "com.service.bookinghotels.entities")
@ComponentScan(basePackages = "com.service.bookinghotels")
public class BookinghotelsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookinghotelsApplication.class, args);
	}
}