package com.example.trainingapp.controllers;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.trainingapp.service.CognitoUserService;



@Controller
public class SettingsController {

    private final CognitoUserService cognitoUserService;
    private final OAuth2AuthorizedClientService authorizedClientService;

    public SettingsController(CognitoUserService cognitoUserService, OAuth2AuthorizedClientService authorizedClientService) {
        this.cognitoUserService = cognitoUserService;
        this.authorizedClientService = authorizedClientService;
    }

    @GetMapping("/settings")
    public String settings() {
        return "settings";
    }

    @PostMapping("/change-password")
    public String changePassword(
            @RequestParam String oldPassword,
            @RequestParam String newPassword,
            Authentication authentication
    ) {
        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
        OAuth2AuthorizedClient authorizedClient = authorizedClientService.loadAuthorizedClient(
                oauthToken.getAuthorizedClientRegistrationId(),
                oauthToken.getName()
        );

        String accessToken = authorizedClient.getAccessToken().getTokenValue();

        cognitoUserService.changeUserPassword(accessToken, oldPassword, newPassword);

        return "redirect:/settings?passwordChanged";
    }

    @PostMapping("/delete-my-account")
    @Transactional
    public String deleteMyAccount(Authentication authentication, HttpSession session) {
        try {
            Object principal = authentication.getPrincipal();
            String email;

            if (principal instanceof OidcUser oidcUser) {
                email = oidcUser.getEmail();
            } else {
                email = authentication.getName();
            }

            System.out.println("Deleting account for email = " + email);

            try {
                if (authentication instanceof OAuth2AuthenticationToken oauthToken) {
                    OAuth2AuthorizedClient authorizedClient = authorizedClientService.loadAuthorizedClient(
                            oauthToken.getAuthorizedClientRegistrationId(),
                            oauthToken.getName()
                    );

                    if (authorizedClient != null && authorizedClient.getAccessToken() != null) {
                        String accessToken = authorizedClient.getAccessToken().getTokenValue();
                        cognitoUserService.deleteUserFromCognito(email);
                    } else {
                        cognitoUserService.deleteUserFromCognito(email);
                    }
                } else {
                    cognitoUserService.deleteUserFromCognito(email);
                }
            } catch (Exception e) {
                System.out.println("Error during Cognito deletion: " + e.getMessage());
                cognitoUserService.deleteUserFromCognito(email);
            }

            session.invalidate();

            return "redirect:/login?accountDeleted";

        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/settings?error=deletion-failed";
        }
    }

}

