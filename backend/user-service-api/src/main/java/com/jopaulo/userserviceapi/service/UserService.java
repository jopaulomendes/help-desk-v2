package com.jopaulo.userserviceapi.service;

import com.jopaulo.userserviceapi.entity.User;
import com.jopaulo.userserviceapi.mapper.UserMapper;
import com.jopaulo.userserviceapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import models.exceptions.ResourceNotFoundException;
import models.requests.CreateUserRequest;
import models.response.UserResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    public UserResponse findById(final String id) {
        return mapper.fromEntity(
                repository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException(
                                "Id: " +id+ " não encontrado.")
                        ));
    }

    public void save(CreateUserRequest createUserRequest) {
        verifyIfEmailExists(createUserRequest.email(), null);
        repository.save(mapper.fromRequest(createUserRequest));
    }

    private void verifyIfEmailExists(final String email, final String id){
        repository.findByEmail(email)
                .filter(user -> !user.getId().equals(id))
                .ifPresent(user -> {throw new DataIntegrityViolationException("E-mail "+email+" já existe na base de dados");
        });
    }

    public List<UserResponse> findAll() {
        return repository.findAll().stream().map(mapper::fromEntity).toList();
    }
}
