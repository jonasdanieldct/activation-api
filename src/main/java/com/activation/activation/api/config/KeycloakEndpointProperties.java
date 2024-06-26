package com.activation.activation.api.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix ="keycloak")
@Data public class KeycloakEndpointProperties {
    private String baseUrl;
    private String token;
    private String createUser;
    private String getUserDetails;
    private String role;
}
