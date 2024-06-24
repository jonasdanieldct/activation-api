package com.activation.activation_api.service.impl;

import com.activation.activation_api.model.Keycloak;
import com.activation.activation_api.service.KeycloakService;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service public class keycloakService implements KeycloakService {
    @Override
    public String getAccessToken(Keycloak keycloak) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/realms/jonaslocal/protocol/openid-connect/token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("username", keycloak.getUsername());
        map.add("password", keycloak.getPassword());
        map.add("client_id", keycloak.getClientId());
        map.add("grant_type", keycloak.getGrantType());

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(map, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST,requestEntity,String.class);
        return response.getBody();
    }
}
