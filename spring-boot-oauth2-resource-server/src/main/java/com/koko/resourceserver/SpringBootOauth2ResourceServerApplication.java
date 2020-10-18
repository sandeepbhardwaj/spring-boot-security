package com.koko.resourceserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableResourceServer
@SpringBootApplication
public class SpringBootOauth2ResourceServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootOauth2ResourceServerApplication.class, args);
	}

}
