package com.jopaulo.userserviceapi.service;

import com.jopaulo.userserviceapi.entity.User;
import com.jopaulo.userserviceapi.mapper.UserMapper;
import com.jopaulo.userserviceapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import models.exceptions.ResourceNotFoundException;
import models.requests.CreateUserRequest;
import models.response.UserResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    public UserResponse findById(final String id) {
        return mapper.fromEntity(
                repository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException(
                                "Código: " +id+ " não encontrado.")
                        ));
    }

    public void save(CreateUserRequest createUserRequest) {
        repository.save(mapper.fromRequest(createUserRequest));
    }
}
