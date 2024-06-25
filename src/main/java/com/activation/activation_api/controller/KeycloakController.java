package com.activation.activation_api.controller;

import com.activation.activation_api.model.AccessToken;
import com.activation.activation_api.model.Keycloak;
import com.activation.activation_api.model.request.CreateKeycloakUserRequest;
import com.activation.activation_api.service.KeycloakService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.HttpHead;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/keycloak")
public class KeycloakController {

    private static final Logger logger = LoggerFactory.getLogger(KeycloakController.class);

    @Autowired
    KeycloakService keycloakService;

    @GetMapping("/accessToken")
    public AccessToken getAccessToken(){
        return keycloakService.generateAccessToken();
    }

    @GetMapping("/create")
    public void createUser(@RequestBody CreateKeycloakUserRequest createKeycloakUserRequest){
        keycloakService.createKeycloakUser(createKeycloakUserRequest);
    }

}


