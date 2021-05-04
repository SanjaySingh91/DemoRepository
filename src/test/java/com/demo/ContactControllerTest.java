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
import org.springframework.web.client.RestTemplate;
import com.demo.entities.Contact;

// @RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ContactControllerTest {

    @LocalServerPort
    int randomServerPort;

    @Test
    public void testUserDashboardSuccess() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:" + randomServerPort + "/user/index/";
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
    public void testAddContactSuccess() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:" + randomServerPort + "/user/addContact/";
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
    public void testRegisterContactSuccess() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:" + randomServerPort + "/user/registerContact/";
        URI uri = new URI(baseUrl);
        Contact contact = new Contact();
        contact.setContactId(11);
        contact.setCustName("sanjay");
        contact.setAddress("hyderabad");
        contact.setCustCity("hyderabad");
        contact.setEmail("sanjay@gmail.com");
        contact.setMobileNo("8125729817");

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");

        HttpEntity<Contact> request = new HttpEntity<>(contact, headers);

        ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);

        System.out.println("contact register result::" + result.getStatusCode()); // Verify request
                                                                                  // succeed
        Assertions.assertEquals(302, result.getStatusCodeValue());
    }


    @Test
    public void testShowContactSuccess() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();

        final String baseUrl = "http://localhost:" + randomServerPort + "/user/showContact/";
        URI uri = new URI(baseUrl);

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-LOCATION", "USA");

        HttpEntity<Contact> requestEntity = new HttpEntity<>(null, headers);

        ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, String.class);

        System.out.println("result::" + result.getStatusCode());
        // Verify request succeed
        Assertions.assertEquals(200, result.getStatusCodeValue());

    }

    @Test
    public void testDeleteContactSuccess() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:" + randomServerPort + "/user/deleteContact/11/";
        URI uri = new URI(baseUrl);

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");

        HttpEntity<Contact> requestEntity = new HttpEntity<>(null, headers);

        ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, String.class);

        System.out.println("result::" + result.getStatusCode());
        // Verify request succeed
        Assertions.assertEquals(200, result.getStatusCodeValue());
    }

    @Test
    public void testUpdateContactSuccess() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:" + randomServerPort + "/user/update-contact/11/";
        URI uri = new URI(baseUrl);

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");

        HttpEntity<Contact> requestEntity = new HttpEntity<>(null, headers);

        ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, String.class);

        System.out.println("result::" + result.getStatusCode());
        // Verify request succeed
        Assertions.assertEquals(200, result.getStatusCodeValue());
    }


    @Test
    public void testUpdateProcessContactSuccess() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:" + randomServerPort + "/user/process-update/";
        URI uri = new URI(baseUrl);
        Contact contact = new Contact();
        contact.setContactId(11);
        contact.setCustName("sanjay");
        contact.setAddress("hyderabad");
        contact.setCustCity("hyderabad");
        contact.setEmail("sanjay@gmail.com");
        contact.setMobileNo("8125729817");

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");

        HttpEntity<Contact> request = new HttpEntity<>(contact, headers);

        ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);

        System.out.println("result::" + result.getStatusCode()); // Verify request succeed
        Assertions.assertEquals(302, result.getStatusCodeValue());
    }



}
