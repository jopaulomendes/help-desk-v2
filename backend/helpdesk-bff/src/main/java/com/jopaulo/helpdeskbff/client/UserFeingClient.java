package com.jopaulo.helpdeskbff.client;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import models.requests.CreateUserRequest;
import models.requests.UpdateUserRequest;
import models.response.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(
        name = "user-service-api",
        path = "/api/users"
)
public interface UserFeingClient {

    @GetMapping("/{id}")
    ResponseEntity<UserResponse> findById(@PathVariable(name = "id") final String id);

    @PostMapping
    ResponseEntity<Void> save(@Valid @RequestBody final CreateUserRequest createUserRequest);

    @GetMapping
    ResponseEntity<List<UserResponse>> findAll();

    @PutMapping("/{id}")
    ResponseEntity<UserResponse> update(
            @PathVariable final String id,
            @Valid @RequestBody final UpdateUserRequest updateUserRequest
    );
}
