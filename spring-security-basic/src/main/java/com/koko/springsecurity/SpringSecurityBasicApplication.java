package com.koko.springsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
//@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class SpringSecurityBasicApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityBasicApplication.class, args);
	}

}
