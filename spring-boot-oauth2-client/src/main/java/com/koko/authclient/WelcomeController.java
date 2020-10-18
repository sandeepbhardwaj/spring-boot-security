package com.koko.authclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/")
public class WelcomeController {
	@Autowired
	OAuth2AuthorizedClientService clientService;

	@GetMapping("welcome")
	public String home() {
		return "{\"message\":\"Hello From oauth2 client\"}";
	}


	@GetMapping("/user")
	public Map<String, String> user() {
		Map<String, String> map = new LinkedHashMap<>();

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;

		//map.put("OAuth2AuthenticationToken",oauthToken.toString());

		OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(oauthToken.getAuthorizedClientRegistrationId(),
				oauthToken.getName());

		String accessToken = client.getAccessToken().getTokenValue();

		map.put("access_token", client.getAccessToken().getTokenValue());
		map.put("issued_at", client.getAccessToken().getIssuedAt().toString());
		map.put("expires_at", Duration.between(client.getAccessToken().getExpiresAt(), client.getAccessToken().getIssuedAt()).toString());
		map.put("refresh_token", client.getRefreshToken().getTokenValue());
		map.put("refresh_issued_at", client.getRefreshToken().getIssuedAt().toString());
		return map;
	}


}
