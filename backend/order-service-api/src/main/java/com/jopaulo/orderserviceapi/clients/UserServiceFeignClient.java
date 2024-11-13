package com.jopaulo.orderserviceapi.clients;

import io.swagger.v3.oas.annotations.Parameter;
import models.response.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "user-service-api",
        url = "http://localhost:8080/api/users"
)
public interface UserServiceFeignClient {
    @GetMapping("/{id}")
    ResponseEntity<UserResponse> findById(
            @PathVariable final String id
    );
}
