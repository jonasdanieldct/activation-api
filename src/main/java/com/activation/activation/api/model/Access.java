package com.activation.activation.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Access {
    private boolean manageGroupMembership;
    private boolean view;
    private boolean mapRoles;
    private boolean impersonate;
    private boolean manage;
}

