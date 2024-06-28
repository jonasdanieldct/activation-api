package com.activation.activation.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data public class AccessToken {
    @JsonProperty(value = "access_token") private String accessToken;
    @JsonProperty(value = "expires_in") private int expiresIn;
    @JsonProperty(value = "refresh_expires_in") private int refreshExpiresIn;
    @JsonProperty(value = "refresh_token") private String refreshToken;
}
