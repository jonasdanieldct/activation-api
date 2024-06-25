package com.activation.activation_api.service.impl;

import com.activation.activation_api.config.KeycloakProperties;
import com.activation.activation_api.model.AccessToken;
import com.activation.activation_api.model.Keycloak;
import com.activation.activation_api.model.request.CreateKeycloakUserRequest;
import com.activation.activation_api.service.KeycloakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service public class KeycloakServiceImpl implements KeycloakService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    KeycloakProperties keycloakProperties;

    public AccessToken generateAccessToken() {
        Keycloak keycloak = new Keycloak("jonas","daniel","local-client","password");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("username", keycloak.getUsername());
        map.add("password", keycloak.getPassword());
        map.add("client_id", keycloak.getClientId());
        map.add("grant_type", keycloak.getGrantType());

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(map, headers);

        ResponseEntity<AccessToken> response = restTemplate.exchange(keycloakProperties.getToken(), HttpMethod.POST,requestEntity,AccessToken.class);
        return response.getBody();
    }

    public String createKeycloakUser(CreateKeycloakUserRequest createKeycloakUserRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(generateAccessToken().getAccessToken());

        HttpEntity<CreateKeycloakUserRequest> requestEntity = new HttpEntity<>(createKeycloakUserRequest, headers);
        return restTemplate.exchange(keycloakProperties.getCreateUser(),HttpMethod.POST,requestEntity,String.class).toString();
    }
}
