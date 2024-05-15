package com.jopaulo.userserviceapi.service;

import com.jopaulo.userserviceapi.entity.User;
import com.jopaulo.userserviceapi.mapper.UserMapper;
import com.jopaulo.userserviceapi.repository.UserRepository;
import models.exceptions.ResourceNotFoundException;
import models.requests.CreateUserRequest;
import models.requests.UpdateUserRequest;
import models.response.UserResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

import static com.jopaulo.userserviceapi.creator.CreatorUtils.generetMock;
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
        when(mapper.fromEntity(any(User.class))).thenReturn(generetMock(UserResponse.class));

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

//    @Test
//    void whenCallFindAllThenReturnListOfUserResponse() {
//        when(repository.findAll()).thenReturn(List.of(new User(), new User()));
//        when(mapper.fromEntity(any(User.class))).thenReturn(mock(UserResponse.class));
//
//        var response = service.findAll();
//
//        assertNotNull(response);
//        assertEquals(2, response.size());
//        assertEquals(UserResponse.class, response.get(0).getClass());
//
//        verify(repository, times(1)).findAll();
//        verify(mapper, times(2)).fromEntity(any(User.class));
//    }

//    @Test
//    void whenCallSaveThenSuccess() {
//        final var request = generetMock(CreateUserRequest.class);
//
//        when(mapper.fromRequest(any())).thenReturn(new User());
//        when(encoder.encode(anyString())).thenReturn("encoded");
//        when(repository.save(any(User.class))).thenReturn(new User());
//        when(repository.findByEmail(anyString())).thenReturn(Optional.empty());
//
//        service.save(request);
//
//        verify(mapper).fromRequest(request);
//        verify(encoder).encode(request.password());
//        verify(repository).save(any(User.class));
//        verify(repository).findByEmail(request.email());
//    }

    @Test
    void whenCallSaveWithInvalidEmailThenThrowDataIntegrityViolationException() {
        final var request = generetMock(CreateUserRequest.class);
        final var entity = generetMock(User.class);

        when(repository.findByEmail(anyString())).thenReturn(Optional.of(entity));

        try {
            service.save(request);
        } catch (Exception e) {
            assertEquals(DataIntegrityViolationException.class, e.getClass());
            assertEquals("E-mail "+request.email()+" já cadastrado.", e.getMessage());
        }

        verify(repository).findByEmail(request.email());
        verify(mapper, times(0)).fromRequest(request);
        verify(encoder, times(0)).encode(request.password());
        verify(repository, times(0)).save(any(User.class));
    }
    @Test
    void whenCallUpdateWithInvalidIdThenThrowResourceNotFoundException() {
        final var request = generetMock(UpdateUserRequest.class);

        when(repository.findById(anyString())).thenReturn(Optional.empty());

        try {
            service.update("1", request);
        } catch (Exception e) {
            assertEquals(ResourceNotFoundException.class, e.getClass());
            assertEquals("Id: 1 não encontrado.", e.getMessage());
        }

        verify(repository).findById(anyString());
        verify(mapper, times(0)).update(any(), any());
        verify(encoder, times(0)).encode(request.password());
        verify(repository, times(0)).save(any(User.class));
    }

    @Test
    void whenCallUpdateWithInvalidEmailThenThrowDataIntegrityViolationException() {
        final var request = generetMock(UpdateUserRequest.class);
        final var entity = generetMock(User.class);

        when(repository.findById(anyString())).thenReturn(Optional.of(entity));
        when(repository.findByEmail(anyString())).thenReturn(Optional.of(entity));

        try {
            service.update("1", request);
        } catch (Exception e) {
            assertEquals(DataIntegrityViolationException.class, e.getClass());
            assertEquals("E-mail " + request.email() + " já cadastrado.", e.getMessage());
        }

        verify(repository).findById(anyString());
        verify(repository).findByEmail(request.email());
        verify(mapper, times(0)).update(any(), any());
        verify(encoder, times(0)).encode(request.password());
        verify(repository, times(0)).save(any(User.class));
    }

    @Test
    void whenCallUpdateWithValidParamsThenGetSuccess() {
        final var id = "1";
        final var request = generetMock(UpdateUserRequest.class);
        final var entity = generetMock(User.class).withId(id);

        when(repository.findById(anyString())).thenReturn(Optional.of(entity));
        when(repository.findByEmail(anyString())).thenReturn(Optional.of(entity));
        when(mapper.update(any(), any())).thenReturn(entity);
        when(repository.save(any(User.class))).thenReturn(entity);

        service.update(id, request);

        verify(repository).findById(anyString());
        verify(repository).findByEmail(request.email());
        verify(mapper).update(request, entity);
        verify(encoder).encode(request.password());
        verify(repository).save(any(User.class));
        verify(mapper).fromEntity(any(User.class));

    }

}