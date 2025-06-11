package com.example.trainingapp.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminDeleteUserRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.ChangePasswordRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.CognitoIdentityProviderException;

@Service
public class CognitoUserService {

    private final CognitoIdentityProviderClient cognitoClient;

    @Value("${aws.cognito.userPoolId}")
    private String userPoolId;

    public CognitoUserService(CognitoIdentityProviderClient cognitoClient) {
        this.cognitoClient = cognitoClient;
    }

    public void changeUserPassword(String accessToken, String oldPassword, String newPassword) {
        ChangePasswordRequest request = ChangePasswordRequest.builder()
                .accessToken(accessToken)
                .previousPassword(oldPassword)
                .proposedPassword(newPassword)
                .build();

        cognitoClient.changePassword(request);
    }

    public void deleteUserFromCognito(String username) {
        try {
            AdminDeleteUserRequest deleteUserRequest = AdminDeleteUserRequest.builder()
                    .userPoolId(userPoolId)
                    .username(username)
                    .build();

            cognitoClient.adminDeleteUser(deleteUserRequest);
        } catch (CognitoIdentityProviderException e) {
            throw new RuntimeException("Failed to delete user from Cognito: " + e.getMessage(), e);
        }
    }
}