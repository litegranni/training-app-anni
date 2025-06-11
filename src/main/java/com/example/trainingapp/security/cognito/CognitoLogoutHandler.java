package com.example.trainingapp.security.cognito;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;

@Component
public class CognitoLogoutHandler implements LogoutSuccessHandler {

    private static final Logger logger = LoggerFactory.getLogger(CognitoLogoutHandler.class);

    @Value("${cognito.domain:https://eu-north-1grprrzbye.auth.eu-north-1.amazoncognito.com}")
    private String domain;

    @Value("${cognito.logout-redirect-url:http://localhost:8080/}")
    private String logoutRedirectUrl;

    @Value("${cognito.client-id:75thj9ggnv6osdv26hhev5i4oc}")
    private String userPoolClientId;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                                Authentication authentication) throws IOException, ServletException {

        logger.info("CognitoLogoutHandler called - processing logout");

        String logoutUrl = UriComponentsBuilder
                .fromUri(URI.create(domain + "/logout"))
                .queryParam("client_id", userPoolClientId)
                .queryParam("logout_uri", logoutRedirectUrl)
                .encode(StandardCharsets.UTF_8)
                .build()
                .toUriString();

        logger.info("Redirecting to Cognito logout URL: {}", logoutUrl);

        if (request.getSession(false) != null) {
            request.getSession().invalidate();
        }
        response.sendRedirect(logoutUrl);
    }
}