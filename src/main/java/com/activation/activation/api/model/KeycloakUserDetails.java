package com.activation.activation.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder public class KeycloakUserDetails {
    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private boolean emailVerified;
    private String createTimestamp;
    private boolean enabled;
    private boolean totp;
    private List<String> disabledCredentialsTypes;
    private List<String> requiredActions;
    private int notBefore;
    private Access access;
}
