package com.jopaulo.userserviceapi.controller.impl;

import com.jopaulo.userserviceapi.controller.UserController;
import com.jopaulo.userserviceapi.entity.User;
import com.jopaulo.userserviceapi.service.UserService;
import lombok.RequiredArgsConstructor;
import models.response.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserService service;
    @Override
    public ResponseEntity<UserResponse> findById(String id) {
        return ResponseEntity.ok().body(service.findById(id));
    }
}
