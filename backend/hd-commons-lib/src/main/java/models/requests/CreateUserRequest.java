package models.requests;

import lombok.With;
import models.enums.ProfileEnum;

import java.util.Set;
@With
public record CreateUserRequest(
        String nome,
        String email,
        String password,
        Set<ProfileEnum> profile
) {
}
