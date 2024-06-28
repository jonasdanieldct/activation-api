package com.activation.activation.api.controller;

import com.activation.activation.api.exception.NoUserFoundException;
import com.activation.activation.api.model.AccessToken;
import com.activation.activation.api.model.KeycloakUserDetails;
import com.activation.activation.api.model.request.CreateKeycloakUserRequest;
import com.activation.activation.api.service.KeycloakService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/create")
    public void createUser(@RequestBody CreateKeycloakUserRequest createKeycloakUserRequest){
        keycloakService.createKeycloakUser(createKeycloakUserRequest);
    }

    @GetMapping("/{username}")
    public KeycloakUserDetails getUserDetails(@PathVariable String username){
        return keycloakService.getUserDetails(username);
    }

}


