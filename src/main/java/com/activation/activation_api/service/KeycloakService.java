package com.activation.activation_api.service;

import com.activation.activation_api.model.Keycloak;

public interface KeycloakService {
    public String getAccessToken(Keycloak keycloak);
}
