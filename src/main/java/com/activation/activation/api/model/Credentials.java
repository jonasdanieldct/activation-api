package com.activation.activation.api.model;

import lombok.Data;

@Data public class Credentials {
    public boolean temporary;
    public String type;
    public String value;
}
