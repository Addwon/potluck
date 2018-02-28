package com.assignments.potluck;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PotluckApplication {
/*
	@Autowired
	UserRepository userRepository;

	@Autowired
	ItemRepository itemRepository;*/

	public static void main(String[] args) {
		SpringApplication.run(PotluckApplication.class, args);
	}

	/*@Override
	public void run(String... args) throws Exception {
		// Cleanup Database tables


	}*/
}
