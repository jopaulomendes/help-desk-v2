package com.jopaulo.userserviceapi.mapper;


import com.jopaulo.userserviceapi.entity.User;
import models.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = IGNORE,
        nullValueCheckStrategy = ALWAYS

)
public interface UserMapper {
    UserResponse fromEntity(final User entity);
}
