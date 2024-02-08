package models.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serial;
import java.io.Serializable;

public record AuthenticateRequest(

        @Schema(description = "E-mail do usuário", example = "fulano@mail.com")
        @Email(message = "E-mail inválido")
        @NotBlank(message = "O E-mail do usuário é obrigatório")
        @Size(max = 50, message = "O nome do usuário deve ter no máximo 50 caracteres")
        String email,

        @Schema(description = "Senha do usuário", example = "123456")
        @Size(min = 6, message = "A senha do usuário deve ter no mínimo 6 caracteres")
        @Size(max = 50, message = "A senha do usuário deve ter no máximo 50 caracteres")
        @NotBlank(message = "A senha do usuário é obrigatório")
        String password
) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
}
