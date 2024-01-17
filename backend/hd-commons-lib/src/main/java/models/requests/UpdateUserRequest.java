package models.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.With;
import models.enums.ProfileEnum;

import java.util.Set;

@With
public record UpdateUserRequest(
        @Schema(description = "Nome do usuário", example = "Fulano de tal")
        @Size(min = 4, message = "O nome do usuário deve ter no mínimo 4 caracteres")
        @Size(max = 50, message = "O nome do usuário deve ter no máximo 50 caracteres")
        String name,
        @Schema(description = "E-mail do usuário", example = "fulano@mail.com")
        @Email(message = "E-mail inválido")
        @Size(max = 50, message = "O nome do usuário deve ter no máximo 50 caracteres")
        String email,
        @Schema(description = "Senha do usuário", example = "123456")
        @Size(min = 6, message = "A senha do usuário deve ter no mínimo 6 caracteres")
        @Size(max = 12, message = "A senha do usuário deve ter no máximo 12 caracteres")
        String password,
        @Schema(description = "Perfil do usuário", example = "[\"ROLE_ADMIN\", \"ROLE_CUSTOMER\", \"ROLE_TECHICIAN\"]")
        Set<ProfileEnum> profile
) {
}
