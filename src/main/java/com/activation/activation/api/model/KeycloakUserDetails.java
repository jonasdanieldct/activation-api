package com.activation.activation.api.model;

import lombok.Data;
import java.util.List;

@Data public class KeycloakUserDetails {
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
