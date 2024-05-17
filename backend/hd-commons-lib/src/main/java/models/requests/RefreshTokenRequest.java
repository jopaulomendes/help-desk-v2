package models.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RefreshTokenRequest(
        @Size(min = 16, max = 50, message = "O Refres Token deve ter entre 16 e 50 caracteres")
        @NotBlank(message = "O Refres Token é obrigatório")
        String refreshToken
) {
}
