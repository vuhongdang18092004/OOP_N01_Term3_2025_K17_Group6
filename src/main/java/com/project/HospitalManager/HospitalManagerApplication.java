package com.project.HospitalManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HospitalManagerApplication {

	public static void main(String[] args) {
		try {
			SpringApplication.run(HospitalManagerApplication.class, args);
		} catch (Exception e) {
			System.err.println("Error starting the application: " + e.getMessage());
			e.printStackTrace();
		}
	}

}
