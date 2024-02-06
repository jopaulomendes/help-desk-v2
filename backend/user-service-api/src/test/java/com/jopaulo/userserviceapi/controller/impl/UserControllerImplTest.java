package com.jopaulo.userserviceapi.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jopaulo.userserviceapi.entity.User;
import com.jopaulo.userserviceapi.repository.UserRepository;
import models.requests.CreateUserRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static com.jopaulo.userserviceapi.creator.CreatorUtils.generetMock;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class UserControllerImplTest {

    public static final String BASE_URI = "/api/users";
    public static final String VALID_EMAIL = "email_teste@mail.com";
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository repository;

    @Test
    void testFindByIdWithSuccess() throws Exception {
        final var entity = generetMock(User.class);
        final var userId = repository.save(entity).getId();

        mockMvc.perform(get("/api/users/{id}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(entity.getId()))
                .andExpect(jsonPath("$.name").value(entity.getName()))
                .andExpect(jsonPath("$.email").value(entity.getEmail()))
                .andExpect(jsonPath("$.password").value(entity.getPassword()))
                .andExpect(jsonPath("$.profile").isArray());

        repository.deleteById(userId);
    }

    @Test
    void testFindByIdWithNotFoundException() throws Exception {
        mockMvc.perform(get("/api/users/{id}", "123"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Id: 123 não encontrado."))
                .andExpect(jsonPath("$.error").value(NOT_FOUND.getReasonPhrase()))
                .andExpect(jsonPath("$.path").value("/api/users/123"))
                .andExpect(jsonPath("$.status").value(NOT_FOUND.value()))
                .andExpect(jsonPath("$.timestamp").isNotEmpty());
    }

    @Test
    void testFindAllWithSuccess() throws Exception {
        final var entity1 = generetMock(User.class);
        final var entity2 = generetMock(User.class);

        repository.saveAll(List.of(entity1, entity2));

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.[0]").isNotEmpty())
                .andExpect(jsonPath("$.[1]").isNotEmpty())
                .andExpect(jsonPath("$.[0].profile").isArray())
                .andExpect(jsonPath("$.[1].profile").isArray());

        repository.deleteAll(List.of(entity1, entity2));
    }

    @Test
    void testSaveUserWithSuccess() throws Exception {
        final var validEmail = "email_teste@mail.com";
        final var request = generetMock(CreateUserRequest.class).withEmail(validEmail);

        mockMvc.perform(post("/api/users")
                .contentType(APPLICATION_JSON)
                .content(toJson(request)))
                .andExpect(status().isCreated());

        repository.deleteByEmail(validEmail);
    }

    @Test
    void testSaveUserWithConflict() throws Exception {
        final var entity = generetMock(User.class).withEmail(VALID_EMAIL);

        repository.save(entity);

        final var request = generetMock(CreateUserRequest.class).withEmail(VALID_EMAIL);

        mockMvc.perform(post(BASE_URI)
                        .contentType(APPLICATION_JSON)
                        .content(toJson(request)))
                        .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").value("E-mail "+ VALID_EMAIL +" já cadastrado."))
                .andExpect(jsonPath("$.error").value(CONFLICT.getReasonPhrase()))
                .andExpect(jsonPath("$.path").value(BASE_URI))
                .andExpect(jsonPath("$.status").value(CONFLICT.value()))
                .andExpect(jsonPath("$.timestamp").isNotEmpty());

        repository.deleteById(entity.getId());
    }

    private String toJson(final Object object) throws Exception {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (final Exception e) {
            throw new Exception("Error to convert object to json", e);
        }
    }
}