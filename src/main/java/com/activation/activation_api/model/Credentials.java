package com.activation.activation_api.model;

import lombok.Data;

@Data public class Credentials {
    public boolean temporary;
    public String type;
    public String value;
}
