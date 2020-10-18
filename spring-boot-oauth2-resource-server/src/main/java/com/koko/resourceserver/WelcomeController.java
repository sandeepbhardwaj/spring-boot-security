package com.koko.resourceserver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/")
public class WelcomeController {

	@GetMapping("welcome")
	public String home() {
		return "{\"message\":\"Hello From oauth2 resource server\"}";
	}
}
