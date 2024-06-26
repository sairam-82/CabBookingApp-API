package com.sairam.cabBookingApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class CabBookingAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CabBookingAppApplication.class, args);
	}

	@GetMapping("/demo")
	public String getResponse(){
		return "Jai Shree Ram";
	}

}
