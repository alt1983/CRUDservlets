package com.aston;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


@Testcontainers
public class ServletsIntegrationTests {

    @Container
    private static final GenericContainer<?> myApp = new GenericContainer<>("crud9:latest9").withExposedPorts(8080);

    @BeforeAll
    public static void setUp() {
    }

    @Test
    void contextLoads() {

    }

    @Test
    void getTransferResponseTest() {

        TestRestTemplate restTemplate = new TestRestTemplate();
        Object request = new Object();
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:" + myApp.getMappedPort(8080) + "/api/posts", request, String.class);
        Assertions.assertNotNull(response);

    }


}
