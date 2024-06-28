package com.activation.activation.api.model;

import lombok.Data;

@Data
public class Access {
    private boolean manageGroupMembership;
    private boolean view;
    private boolean mapRoles;
    private boolean impersonate;
    private boolean manage;
}

