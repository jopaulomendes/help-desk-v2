package com.jopaulo.userserviceapi.service;

import com.jopaulo.userserviceapi.entity.User;
import com.jopaulo.userserviceapi.mapper.UserMapper;
import com.jopaulo.userserviceapi.repository.UserRepository;
import models.exceptions.ResourceNotFoundException;
import models.response.UserResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceTest {

    @InjectMocks
    private UserService service;
    @Mock
    private UserRepository repository;
    @Mock
    private UserMapper mapper;
    @Mock
    private BCryptPasswordEncoder encoder;

    @Test
    void whenCallFindByIdWithValidThenReturnUserResponse(){
        when(repository.findById(anyString())).thenReturn(Optional.of(new User()));
        when(mapper.fromEntity(any(User.class))).thenReturn(mock(UserResponse.class));

        final var response = service.findById("1");
        assertNotNull(response);
        assertEquals(UserResponse.class, response.getClass());
        verify(repository, times(1)).findById(anyString());
        verify(mapper, times(1)).fromEntity(any(User.class));
    }

    @Test
    void whenCallFindByIdWithInvalidIdThenThrowResourceNotFoundException() {
        when(repository.findById(anyString())).thenReturn(Optional.empty());

        try {
            service.findById("1");
        } catch (Exception e) {
            assertEquals(ResourceNotFoundException.class, e.getClass());
            assertEquals("Id: 1 não encontrado.", e.getMessage());
        }

        verify(repository, times(1)).findById(anyString());
        verify(mapper, times(0)).fromEntity(any(User.class));
    }
}