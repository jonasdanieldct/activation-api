package com.activation.activation_api.model.request;


import com.activation.activation_api.model.Attributes;
import com.activation.activation_api.model.Credentials;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data@NoArgsConstructor@AllArgsConstructor
public class CreateKeycloakUserRequest {
    private Attributes attributes;
    private List<Credentials> credentials;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private boolean emailVerified;
    private boolean enabled;
}
