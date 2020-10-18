package com.koko.oaut2;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;

@Data
@Builder
public class TokenStore {
    OidcIdToken idToken;
    OAuth2AccessToken accessToken;
    OAuth2RefreshToken refreshToken;
}
