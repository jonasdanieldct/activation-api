package com.activation.activation_api.service;

import com.activation.activation_api.model.AccessToken;
import com.activation.activation_api.model.request.CreateKeycloakUserRequest;

public interface KeycloakService {
    public AccessToken generateAccessToken();
    public String createKeycloakUser(CreateKeycloakUserRequest createKeycloakUserRequest);
}
