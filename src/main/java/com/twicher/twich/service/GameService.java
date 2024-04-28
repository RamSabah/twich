package com.twicher.twich.service;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GameService {

    public String createToken(String client, String secret) {
        String tokenEndpoint = "https://id.twitch.tv/oauth2/token";
        String grantType = "client_credentials";

        String requestBody = "client_id=" + client + "&client_secret=" + secret + "&grant_type=" + grantType;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(tokenEndpoint, requestEntity, String.class);

        String responseBody = responseEntity.getBody();
        JSONObject json = new JSONObject(responseBody);
        String accessToken = json.getString("access_token");

        return accessToken;
    }


}
