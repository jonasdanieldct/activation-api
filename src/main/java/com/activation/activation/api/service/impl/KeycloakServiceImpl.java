package com.activation.activation.api.service.impl;

import com.activation.activation.api.config.KeycloakProperties;
import com.activation.activation.api.config.KeycloakEndpointProperties;
import com.activation.activation.api.exception.NoUserFoundException;
import com.activation.activation.api.model.AccessToken;
import com.activation.activation.api.model.KeycloakRole;
import com.activation.activation.api.model.KeycloakUserDetails;
import com.activation.activation.api.model.request.CreateKeycloakUserRequest;
import com.activation.activation.api.service.KeycloakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Service public class KeycloakServiceImpl implements KeycloakService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    KeycloakEndpointProperties keycloakEndpointProperties;

    @Autowired
    KeycloakProperties keycloakProperties;

    public static final Logger logger = LoggerFactory.getLogger(KeycloakServiceImpl.class);

    public AccessToken generateAccessToken() {
        logger.info("KeycloakServiceImpl.generateAccessToken");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("username", keycloakProperties.getUsername());
        map.add("password", keycloakProperties.getPassword());
        map.add("client_id", keycloakProperties.getClientId());
        map.add("grant_type", keycloakProperties.getGrantType());

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(map, headers);

        ResponseEntity<AccessToken> response = restTemplate.exchange(keycloakEndpointProperties.getToken(), HttpMethod.POST, requestEntity, AccessToken.class);
        return response.getBody();
    }

    public String createKeycloakUser(CreateKeycloakUserRequest createKeycloakUserRequest) {
        logger.info("KeycloakServiceImpl.getuserDetails (createKeycloakUser :{})", createKeycloakUserRequest);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(generateAccessToken().getAccessToken());

        HttpEntity<CreateKeycloakUserRequest> requestEntity = new HttpEntity<>(createKeycloakUserRequest, headers);
        return restTemplate.exchange(keycloakEndpointProperties.getCreateUser(), HttpMethod.POST, requestEntity, String.class).toString();
    }

    public KeycloakUserDetails getUserDetails(String username){
        logger.info("KeycloakServiceImpl.getuserDetails (username :{})", username);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(generateAccessToken().getAccessToken());
        List<KeycloakUserDetails> keycloakUsers = new ArrayList<>();

        HttpEntity requestEntity = new HttpEntity(headers);
        String endpoint = keycloakEndpointProperties.getGetUserDetails() + "?username=" + username + "&max=1";
        KeycloakUserDetails keycloakUser = restTemplate.exchange(endpoint, HttpMethod.GET, requestEntity, new ParameterizedTypeReference<List<KeycloakUserDetails>>() {
            }).getBody().stream().filter(user -> user.getUsername().equals(username)).findFirst().orElse(null);

            if (keycloakUser == null) {
                throw new NoUserFoundException("No user found with username: " + username);
            }

        return keycloakUser;
    }

    public List<KeycloakRole> getKeycloakRoles(){
        logger.info("KeycloakServiceImpl.getKeycloakRoles");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(generateAccessToken().getAccessToken());

        HttpEntity requestEntity = new HttpEntity(headers);
        return restTemplate.exchange(keycloakEndpointProperties.getRole(),HttpMethod.GET,requestEntity,new ParameterizedTypeReference<List<KeycloakRole>>() {}).getBody();
    }
}
