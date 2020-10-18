package com.koko.oaut2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
@Qualifier("oktaLogoutHandler")
public class OktaLogoutHandler implements LogoutHandler {

    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;

    @Value("${okta.logout-uri}")
    String logoutUrl;

    @Value("${okta.revoke-uri}")
    String revokeUrl;

    @Override
    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {
        log.debug("executing MySsoLogoutHandler.logout");

        OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
        DefaultOidcUser defaultOidcUser = (DefaultOidcUser) oAuth2AuthenticationToken.getPrincipal();

        String id_token = defaultOidcUser.getIdToken().getTokenValue();
        log.debug("token: {}", id_token);

        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<String> response = restTemplate.getForEntity(logoutUrl, String.class, id_token);
        } catch (HttpClientErrorException e) {
            log.error("HttpClientErrorException invalidating token with SSO authorization server. response.status code: {}, server URL: {}", e.getStatusCode(), logoutUrl);
        }

        //revoke token
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        //headers.add("authorization","Basic");

        OAuth2AuthorizedClient authorizedClient =
                this.authorizedClientService.loadAuthorizedClient("custom-client", authentication.getName());

        OAuth2AccessToken accessToken = authorizedClient.getAccessToken();

        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("client_id", "0oa8hg8xxzspwi7Yc5d5");
        map.add("client_secret", "mEMm87xYp5ZiJksP3QWwKxJTbCliDw4H7CnXrCWC");
        map.add("token", accessToken.getTokenValue());
        map.add("token_type_hint", "access_token");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(revokeUrl, request, String.class);
            log.info("Revoke response status code :{}, body :{} ", response.getStatusCode(), response.getBody());
        } catch (HttpClientErrorException e) {
            log.error("HttpClientErrorException invalidating token with SSO authorization server. response.status code: {}, server URL: {}", e.getStatusCode(), revokeUrl);
        }

    }

}