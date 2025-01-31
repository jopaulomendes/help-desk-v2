package com.jopaulo.helpdeskbff.service;

import com.jopaulo.helpdeskbff.client.UserFeingClient;
import lombok.RequiredArgsConstructor;
import models.requests.CreateUserRequest;
import models.requests.UpdateUserRequest;
import models.response.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserFeingClient feingClient;

    public UserResponse findById(final String id) {
        return feingClient.findById(id).getBody();
    }

    public List<UserResponse> findAll() {
        return feingClient.findAll().getBody();
    }

    public void save(CreateUserRequest request) {
        feingClient.save(request);
    }

    public UserResponse update(final String id, final UpdateUserRequest request) {
        return feingClient.update(id, request).getBody();
    }
}
