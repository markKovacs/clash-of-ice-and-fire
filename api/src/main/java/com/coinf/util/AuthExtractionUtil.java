package com.coinf.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AuthExtractionUtil {

    // TODO: put into shared lib a coinf contants file, to get info from token (keys)

    @SuppressWarnings("unchecked")
    public Map<String, Object> getAdditionalAuthInfo(Authentication authentication) {
        OAuth2AuthenticationDetails oauthDetails = (OAuth2AuthenticationDetails) authentication.getDetails();
        return (Map<String, Object>) oauthDetails.getDecodedDetails();
    }

    public String getGuild(Authentication authentication) {
        return getAdditionalAuthInfo(authentication).get("guild").toString();
    }

    public String getAuthName(Authentication authentication) {
        return getAdditionalAuthInfo(authentication).get("auth-name").toString();
    }
}
