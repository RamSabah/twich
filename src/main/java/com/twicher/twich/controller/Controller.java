package com.twicher.twich.controller;

import com.twicher.twich.service.GameService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.*;

import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class Controller {
    @Autowired
    public GameService gameService;


    @PostMapping("/all")
    public ResponseEntity<String> getAllGames() {

        String clientId = "f93c2emryon37texwxau1j9wztxt4n";
        String clientSecret = "760mwqgp95xehbfl6naf9jvacy4n5q";
        String gameEndpoint = "https://api.igdb.com/v4/games/";

        String getToken = gameService.createToken(clientId, clientSecret);
        // Set headers

        // Create RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // Send POST request to Twitch token endpoint

        System.out.println("RES::" + getToken);
        HttpHeaders headers_2 = new HttpHeaders();
        headers_2.add("Authorization", "Bearer " + getToken);
        headers_2.add("Client-ID", clientId);
        HttpEntity<String> requestEntityGames = new HttpEntity<>("fields *;", headers_2);
        ResponseEntity<String> responseEntityGames = restTemplate.postForEntity(gameEndpoint, requestEntityGames, String.class);
        System.out.println("RES::" + responseEntityGames);

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

}
