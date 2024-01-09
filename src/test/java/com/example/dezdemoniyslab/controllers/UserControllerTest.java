package com.example.dezdemoniyslab.controllers;

import com.example.dezdemoniyslab.models.Role;
import com.example.dezdemoniyslab.models.User;
import com.example.dezdemoniyslab.reposiotries.UserRepository;
import com.example.dezdemoniyslab.requests.users.UserRegisterRequest;
import com.example.dezdemoniyslab.requests.users.UserUpdateRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @BeforeEach
    void createTestUser(){
        if (!userRepository.findAll().isEmpty()){
         return;
        }
        User admin = User.builder()
                .firstname("admin")
                .lastname("admin")
                .username("admin")
                .email("admin@admin.ru")
                .password("admin")
                .role(Role.ADMIN)
                .build();

        userRepository.save(admin);
    }

    @Test
    void testGetUserList() throws Exception {
        ResponseEntity<String> response = this.restTemplate.getForEntity(
                "http://localhost:" + port + "/users/list", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        System.out.println(response.getBody());
    }

    @Test
    public void testGetUser() {
        long userId = userRepository.findByUsernameAndIsDeletedFalse("admin").orElseThrow(
                () -> new IllegalArgumentException("User with name " + "admin"  + " not exists")
        ).getId();
        ResponseEntity<String> response = this.restTemplate.getForEntity(
                "http://localhost:" + port + "/users/" + userId, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        System.out.println(response.getBody());
    }

    @Test
    void testCreateUser() throws Exception {
        UserRegisterRequest userRegisterRequest = UserRegisterRequest.builder()
                .firstname("user")
                .lastname("user")
                .username("user")
                .email("user@user.ru")
                .password("user")
                .role(Role.USER)
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<UserRegisterRequest> request = new HttpEntity<>(userRegisterRequest, headers);

        ResponseEntity<String> response = this.restTemplate.postForEntity(
                "http://localhost:" + port + "/users/create", request, String.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        System.out.println(response.getBody());


    }

    @Test
    void testUpdateUser() throws Exception  {
        long userId = userRepository.findByUsernameAndIsDeletedFalse("admin").orElseThrow(
                () -> new IllegalArgumentException("User with name " + "admin"  + " not exists")
        ).getId();
        UserUpdateRequest userUpdateRequest = UserUpdateRequest.builder()
                .firstname("adminious")
                .lastname("admin")
                .username("admin")
                .email("admin@admin.ru")
                .password("admin")
                .role(Role.ADMIN)
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<UserUpdateRequest> request = new HttpEntity<>(userUpdateRequest, headers);

        ResponseEntity<String> response = this.restTemplate.exchange(
                "http://localhost:" + port + "/users/edit/" + userId, HttpMethod.PATCH, request, String.class);
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        System.out.println(response.getBody());
    }

    @Test
    void testSoftDeleteUser() throws Exception{
        long userId = userRepository.findByUsernameAndIsDeletedFalse("admin").orElseThrow(
                () -> new IllegalArgumentException("User with name " + "admin"  + " not exists")
        ).getId();

        ResponseEntity<String> response = this.restTemplate.exchange(
                "http://localhost:" + port + "/users/delete/" + userId, HttpMethod.DELETE, null, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        System.out.println(response.getBody());
    }
}