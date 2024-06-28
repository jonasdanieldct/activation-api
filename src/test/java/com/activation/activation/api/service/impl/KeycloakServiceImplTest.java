package com.activation.activation.api.service.impl;

import com.activation.activation.api.config.KeycloakEndpointProperties;
import com.activation.activation.api.config.KeycloakProperties;
import com.activation.activation.api.exception.NoUserFoundException;
import com.activation.activation.api.model.Access;
import com.activation.activation.api.model.AccessToken;
import com.activation.activation.api.model.KeycloakUserDetails;
import com.activation.activation.api.service.KeycloakService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.plugins.MockitoLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.*;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc class KeycloakServiceImplTest {

    @MockBean
    KeycloakServiceImpl keycloakService;

    @MockBean
    KeycloakProperties keycloakProperties;

    @Mock KeycloakEndpointProperties keycloakEndpointProperties;

    @Mock
    private Logger loggerMock;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    public void setup(){
        when(keycloakProperties.getUsername()).thenReturn("jonas");
        when(keycloakProperties.getPassword()).thenReturn("daniel");
        when(keycloakProperties.getClientId()).thenReturn("local-client");
        when(keycloakProperties.getGrantType()).thenReturn("password");

    }

    @Test
    void generateAccessToken() {
        when(keycloakService.generateAccessToken()).thenReturn(new AccessToken("access_token",1,2,"refresh_token","token_type"));
        AccessToken accessToken = keycloakService.generateAccessToken();
        assertEquals("access_token",accessToken.getAccessToken());
        assertNotNull(accessToken);
        assertNotNull(accessToken.getAccessToken());

        assertEquals("jonas",keycloakProperties.getUsername());
        assertEquals("daniel",keycloakProperties.getPassword());
        assertEquals("local-client",keycloakProperties.getClientId());
        assertEquals("password",keycloakProperties.getGrantType());

    }


    @Test public void testGenerateAccessToken() {
        // Mock response entity
        AccessToken accessToken = new AccessToken();
        accessToken.setAccessToken("mocked-access-token");
        when(keycloakService.generateAccessToken()).thenReturn(new AccessToken("access_token",1,2,"refresh_token","token_type"));
        AccessToken generatedToken = keycloakService.generateAccessToken();

        ResponseEntity<AccessToken> responseEntity = new ResponseEntity<>(accessToken, HttpStatus.OK);

        // Mock endpoint properties
        when(keycloakEndpointProperties.getToken()).thenReturn("http://localhost:8080/realms/local-realm/protocol/openid-connect/token");

        // Mock restTemplate exchange method
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(HttpEntity.class), eq(AccessToken.class)))
                .thenReturn(responseEntity);

        // Call the method under test


        // Verify the result
        assertEquals("refresh_token", generatedToken.getRefreshToken());
    }
}