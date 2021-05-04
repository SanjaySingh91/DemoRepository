package com.demo;

import java.net.URI;
import java.net.URISyntaxException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import com.demo.entities.User;

// @RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HomeControllerTest {

    @LocalServerPort
    int randomServerPort;

    @Test
    public void testHomeSuccess() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:" + randomServerPort + "/";
        URI uri = new URI(baseUrl);

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");

        HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);

        ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, String.class);

        System.out.println("result::" + result.getStatusCode());
        // Verify request succeed
        Assertions.assertEquals(200, result.getStatusCodeValue());


    }

    @Test
    public void testSignupSuccess() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:" + randomServerPort + "/signup/";
        URI uri = new URI(baseUrl);

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");

        HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);

        ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, String.class);

        System.out.println("result::" + result.getStatusCode());
        // Verify request succeed
        Assertions.assertEquals(200, result.getStatusCodeValue());
    }



    @Test
    public void testSearchUserSuccess() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:" + randomServerPort + "/searchUser/";
        URI uri = new URI(baseUrl);
        User user = new User();
        user.setUserId(11);
        user.setUsername("sanjay dhakar");
        user.setEmail("sanjudhakar@gmail.com");
        user.setPassword("1111");
        user.setRole("User");
        user.setStatus("active");

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");

        HttpEntity<User> request = new HttpEntity<>(user, headers);

        ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);

        System.out.println("result::" + result.getStatusCode());
        // Verify request succeed
        Assertions.assertEquals(200, result.getStatusCodeValue());
    }

    @Test
    public void testAddUserSuccess() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:" + randomServerPort + "/register/";
        URI uri = new URI(baseUrl);
        User user = new User();
        user.setUserId(11);
        user.setUsername("sanjay dhakar");
        user.setEmail("sanjudhakar@gmail.com");
        user.setPassword("1111");
        user.setRole("User");
        user.setStatus("active");

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");

        HttpEntity<User> request = new HttpEntity<>(user, headers);

        ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);

        System.out.println("result::" + result.getStatusCode());
        // Verify request succeed
        Assertions.assertEquals(200, result.getStatusCodeValue());
    }



    @Test
    public void testAddEmployeeMissingHeader() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:" + randomServerPort + "/register/";
        URI uri = new URI(baseUrl);
        User user = new User();
        user.setUserId(11);
        user.setUsername("sanjay dhakar");
        user.setEmail("sanjudhakar@gmail.com");
        user.setPassword("1111");
        user.setRole("User");
        user.setStatus("active");

        HttpHeaders headers = new HttpHeaders();

        HttpEntity<User> request = new HttpEntity<>(user, headers);

        try {
            ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);
            System.out.println("without header result::" + result.getStatusCode());
            System.out.println("------");

        } catch (HttpClientErrorException ex) { // Verify bad request and missing header
            Assertions.assertEquals(400, ex.getRawStatusCode());
            Assertions.assertEquals(true, ex.getResponseBodyAsString()
                    .contains("Missing request header"));
        }
    }


    @Test
    public void testAddEmployeeMissingData() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:" + randomServerPort + "/register/";
        URI uri = new URI(baseUrl);
        User user = new User();

        HttpHeaders headers = new HttpHeaders();

        HttpEntity<User> request = new HttpEntity<>(user, headers);

        try {
            ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);
            System.out.println("without data result::" + result.getStatusCode());
            System.out.println("------");

        } catch (HttpClientErrorException ex) { // Verify bad request and missing header
            Assertions.assertEquals(400, ex.getRawStatusCode());
            Assertions.assertEquals(true, ex.getResponseBodyAsString()
                    .contains("Missing request header"));
        }
    }



    @Test
    public void testGetUserListSuccessWithHeaders() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();

        final String baseUrl = "http://localhost:" + randomServerPort + "/showAllUsers/";
        URI uri = new URI(baseUrl);

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-LOCATION", "USA");

        HttpEntity<User> requestEntity = new HttpEntity<>(null, headers);

        ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, String.class);

        System.out.println("result::" + result.getStatusCode());
        // Verify request succeed
        Assertions.assertEquals(200, result.getStatusCodeValue());

    }

    @Test
    public void testSigninSuccess() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:" + randomServerPort + "/signin/";
        URI uri = new URI(baseUrl);

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");

        HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);

        ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, String.class);

        System.out.println("result::" + result.getStatusCode());
        // Verify request succeed
        Assertions.assertEquals(200, result.getStatusCodeValue());
    }

}
