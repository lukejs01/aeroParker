package com.aeroparker.aeroParker.controllers;

import com.aeroparker.aeroParker.TestConstants;
import com.aeroparker.aeroParker.model.AeroParker;
import com.aeroparker.aeroParker.repository.AeroParkerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AeroParkerControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private AeroParkerRepository repository;

    TestConstants testConstants = new TestConstants();

    @BeforeEach
    void beforeEach() {
        repository.deleteAll();
    }


    /**
     * @verifies save entity from request body
     * @see AeroParkerController#saveUser(com.aeroparker.aeroParker.model.AeroParker)
     */
    @Test
    public void saveUser_shouldSaveEntityFromRequestBody() {
        // given
        AeroParker user = testConstants.createUser("user@email.com",
                "Mr",
                "user",
                "one",
                "address1",
                "address2",
                "Wigan",
                "wn",
                "+447812345678");


        // when
        WebTestClient.ResponseSpec saveCustomer = webTestClient
                .post().uri("/api/v1/aeroparker/user")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(user), AeroParker.class)
                .exchange();

        // then
        AeroParker result = repository.getReferenceById(1);

        saveCustomer.expectStatus().is2xxSuccessful();

        Assertions.assertEquals(1, result.getId());
    }

    /**
     * @verifies return 400 if email already exists
     * @see AeroParkerController#saveUser(com.aeroparker.aeroParker.model.AeroParker)
     */
    @Test
    public void saveUser_shouldReturn400IfEmailAlreadyExists() {
        // given
        AeroParker user = testConstants.createUser("user@email.com",
                "Mr",
                "user",
                "one",
                "address1",
                "address2",
                "Wigan",
                "wn",
                "+447812345678");



        // when
        WebTestClient.ResponseSpec saveCustomer1 = webTestClient
                .post().uri("/api/v1/aeroparker/user")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(user), AeroParker.class)
                .exchange();

        // then

        saveCustomer1.expectStatus().is2xxSuccessful();

        WebTestClient.ResponseSpec saveCustomer2 = webTestClient
                .post().uri("/api/v1/aeroparker/user")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(user), AeroParker.class)
                .exchange();

        saveCustomer2.expectStatus().isBadRequest();
    }

    /**
     * @verifies return 400 if name is incorrect format
     * @see AeroParkerController#saveUser(com.aeroparker.aeroParker.model.AeroParker)
     */
    @Test
    public void saveUser_shouldReturn400IfNameIsIncorrectFormat() {
        // given
        AeroParker user = testConstants.createUser("user@email.com",
                "Mr",
                "1",
                "one",
                "address1",
                "address2",
                "Wigan",
                "wn",
                "+447812345678");


        // when
        WebTestClient.ResponseSpec saveCustomer = webTestClient
                .post().uri("/api/v1/aeroparker/user")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(user), AeroParker.class)
                .exchange();

        // then

        saveCustomer.expectStatus().isBadRequest();
    }

    /**
     * @verifies return 400 if number is incorrect format
     * @see AeroParkerController#saveUser(com.aeroparker.aeroParker.model.AeroParker)
     */
    @Test
    public void saveUser_shouldReturn400IfNumberIsIncorrectFormat() throws Exception {
        // given
        AeroParker user = testConstants.createUser("user@email.com",
                "Mr",
                "1",
                "one",
                "address1",
                "address2",
                "Wigan",
                "wn",
                "phoneNumber");


        // when
        WebTestClient.ResponseSpec saveCustomer = webTestClient
                .post().uri("/api/v1/aeroparker/user")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(user), AeroParker.class)
                .exchange();

        // then

        saveCustomer.expectStatus().isBadRequest();

        user.setPhoneNumber("07812345678");
        WebTestClient.ResponseSpec saveCustomerWithPrefix0 = webTestClient
                .post().uri("/api/v1/aeroparker/user")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(user), AeroParker.class)
                .exchange();

        saveCustomerWithPrefix0.expectStatus().isBadRequest();
    }
}
