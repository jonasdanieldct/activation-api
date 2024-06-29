package com.activation.activation.api.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "keycloak.attribute") public class KeycloakProperties {
    private String username;
    private String password;
    @JsonProperty("client_id")
    private String clientId;
    @JsonProperty("grant_type")
    private String grantType;
    private String uuid;

}
