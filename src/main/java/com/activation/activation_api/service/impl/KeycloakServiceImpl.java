package com.activation.activation_api.service.impl;

import com.activation.activation_api.config.KeycloakEndpointProperties;
import com.activation.activation_api.model.AccessToken;
import com.activation.activation_api.config.KeycloakProperties;
import com.activation.activation_api.model.KeycloakUserDetails;
import com.activation.activation_api.model.request.CreateKeycloakUserRequest;
import com.activation.activation_api.service.KeycloakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service public class KeycloakServiceImpl implements KeycloakService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    KeycloakEndpointProperties keycloakEndpointProperties;

    @Autowired
    KeycloakProperties keycloakProperties;

    public AccessToken generateAccessToken() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("username", keycloakProperties.getUsername());
        map.add("password", keycloakProperties.getPassword());
        map.add("client_id", keycloakProperties.getClientId());
        map.add("grant_type", keycloakProperties.getGrantType());

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(map, headers);

        ResponseEntity<AccessToken> response = restTemplate.exchange(keycloakEndpointProperties.getToken(), HttpMethod.POST,requestEntity,AccessToken.class);
        return response.getBody();
    }

    public String createKeycloakUser(CreateKeycloakUserRequest createKeycloakUserRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(generateAccessToken().getAccessToken());

        HttpEntity<CreateKeycloakUserRequest> requestEntity = new HttpEntity<>(createKeycloakUserRequest, headers);
        return restTemplate.exchange(keycloakEndpointProperties.getCreateUser(),HttpMethod.POST,requestEntity,String.class).toString();
    }

    public List<KeycloakUserDetails> getUserDetails(String username){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(generateAccessToken().getAccessToken());

        HttpEntity requestEntity = new  HttpEntity(headers);
        String endpoint = keycloakEndpointProperties.getGetUserDetails()+ "?username="+username+"&max=1";
        List<KeycloakUserDetails> keycloakUsers = restTemplate.exchange(endpoint, HttpMethod.GET, requestEntity, new ParameterizedTypeReference<List<KeycloakUserDetails>>() {}).getBody();
        return keycloakUsers.stream().filter(user -> user.getUsername().equals(username)).collect(Collectors.toList());
    }
}
