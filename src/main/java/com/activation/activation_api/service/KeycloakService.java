package com.activation.activation_api.service;

import com.activation.activation_api.model.AccessToken;
import com.activation.activation_api.model.KeycloakUserDetails;
import com.activation.activation_api.model.request.CreateKeycloakUserRequest;
import com.activation.activation_api.model.response.KeycloakUserDetailsResponse;

import java.util.List;

public interface KeycloakService {
    public AccessToken generateAccessToken();
    public String createKeycloakUser(CreateKeycloakUserRequest createKeycloakUserRequest);
    public List<KeycloakUserDetails> getUserDetails(String username);
}
