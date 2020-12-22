package com.epam.kata.csvservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({CsvServiceApplication.class})
public class CsvServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CsvServiceApplication.class, args);
	}

}
