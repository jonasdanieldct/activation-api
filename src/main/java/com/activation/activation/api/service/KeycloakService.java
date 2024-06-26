package com.activation.activation.api.service;

import com.activation.activation.api.model.AccessToken;
import com.activation.activation.api.model.KeycloakRole;
import com.activation.activation.api.model.KeycloakUserDetails;
import com.activation.activation.api.model.request.CreateKeycloakUserRequest;


import java.util.List;

public interface KeycloakService {
    public AccessToken generateAccessToken();
    public String createKeycloakUser(CreateKeycloakUserRequest createKeycloakUserRequest);
    public KeycloakUserDetails getUserDetails(String username);

    public List<KeycloakRole> getKeycloakRoles();
}
