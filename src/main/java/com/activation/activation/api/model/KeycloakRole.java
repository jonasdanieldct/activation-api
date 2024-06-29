package com.activation.activation.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KeycloakRole {
    public String id;
    public String name;
    public String description;
    public boolean composite;
    public boolean clientRole;
    public String containerId;
}
