package com.koko.oaut2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/")
public class WebController {


    @GetMapping("/")
    public String index(Model model, Principal principal) {
        return "Hello";
    }

    @GetMapping("securedPage")
    public String securedPage(Model model, Principal principal) {
        return "securedPage";
    }

    @GetMapping("user")
    public OAuth2User securedPage(Principal principal) {
        OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken) principal;
        OAuth2User oAuth2User = oAuth2AuthenticationToken.getPrincipal();
        return oAuth2User;
    }

    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;

    @GetMapping("token")
    public TokenStore token(Authentication authentication) {
        OAuth2AuthorizedClient authorizedClient =
                this.authorizedClientService.loadAuthorizedClient("custom-client", authentication.getName());

        OAuth2AccessToken accessToken = authorizedClient.getAccessToken();
        OAuth2RefreshToken refreshToken = authorizedClient.getRefreshToken();

        OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
        DefaultOidcUser defaultOidcUser = (DefaultOidcUser) oAuth2AuthenticationToken.getPrincipal();
        OidcIdToken idToken = defaultOidcUser.getIdToken();


        TokenStore tokenStore = TokenStore.builder()
                .idToken(idToken)
                .accessToken(accessToken)
                .refreshToken(refreshToken).build();

        return tokenStore;
    }


}