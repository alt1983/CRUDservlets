package com.aston;

import com.aston.dto.PostDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import com.google.gson.Gson;

@Testcontainers
public class ServletsIntegrationTests {

    TestRestTemplate restTemplate = new TestRestTemplate();

    private final String HOST = "http://localhost:";
    private final String SERVICE = "/crudserv100/api/posts";

    @Container
    private static final GenericContainer<?> myApp = new GenericContainer<>("crud101:latest101").withExposedPorts(8080);

    @BeforeAll
    public static void setUp() {
    }

    @Test
    void getResponseTest() {
        ResponseEntity<?> response = restTemplate.getForEntity(HOST + myApp.getMappedPort(8080) + SERVICE, String.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void postResponseTest() {
        final var gson = new Gson();
        PostDto postDtoNew = new PostDto(0l, 1000l, "New Post");
        String request = gson.toJson(postDtoNew);
        ResponseEntity<?> response = restTemplate.postForEntity(HOST + myApp.getMappedPort(8080) + SERVICE, request, String.class);
        String body = response.getBody().toString();
        PostDto postDtoSaved = gson.fromJson(body, PostDto.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(postDtoNew.getContent(), postDtoSaved.getContent());
    }

    @Test
    void deleteResponseTest() {
        final var gson = new Gson();
        PostDto postDtoNew = new PostDto(1l, 1000l, "New Post");
        String request = gson.toJson(postDtoNew);
        ResponseEntity<?> response1 = restTemplate.postForEntity(HOST + myApp.getMappedPort(8080) + SERVICE, request, String.class);
        String body = response1.getBody().toString();
        PostDto postDtoSaved = gson.fromJson(body, PostDto.class);
        long savedId = postDtoSaved.getId();
        ResponseEntity<?> response2 = restTemplate.getForEntity(HOST + myApp.getMappedPort(8080) + SERVICE + "/" + savedId, String.class);
        Assertions.assertEquals(HttpStatus.OK, response2.getStatusCode());
        restTemplate.delete("http://localhost:" + myApp.getMappedPort(8080) + SERVICE + "/" + savedId);
        ResponseEntity<?> response3 = restTemplate.getForEntity(HOST + myApp.getMappedPort(8080) + SERVICE + "/" + savedId, String.class);
        Assertions.assertEquals(HttpStatus.NO_CONTENT, response3.getStatusCode());
    }

    @Test
    void putResponseTest() {
        final var gson = new Gson();
        PostDto postDtoNew = new PostDto(1l, 1000l, "New Post");
        String request1 = gson.toJson(postDtoNew);
        ResponseEntity<?> response1 = restTemplate.postForEntity(HOST + myApp.getMappedPort(8080) + SERVICE, request1, String.class);
        String body1 = response1.getBody().toString();
        PostDto postDtoSaved = gson.fromJson(body1, PostDto.class);
        long savedId = postDtoSaved.getId();
        PostDto postDtoPut = new PostDto(postDtoSaved.getId(), postDtoSaved.getAuthorid(), "Updated Post");
        String request2 = gson.toJson(postDtoPut);
        restTemplate.put(HOST + myApp.getMappedPort(8080) + SERVICE, request2);
        ResponseEntity<?> response3 = restTemplate.getForEntity(HOST + myApp.getMappedPort(8080) + SERVICE + "/" + savedId, String.class);
        String body2 = response3.getBody().toString();
        PostDto postDtoPutSaved = gson.fromJson(body2, PostDto.class);
        Assertions.assertEquals(postDtoPut.getContent(), postDtoPutSaved.getContent());
    }
}
