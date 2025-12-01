package com.niemietz.bakerysample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class BakerySampleApplication {

	public static void main(String[] args) {
        SpringApplication.run(BakerySampleApplication.class, args);
	}
}
