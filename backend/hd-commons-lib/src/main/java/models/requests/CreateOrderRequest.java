package models.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateOrderRequest (
        @Schema(description = "Request Id", example = "664424d1c2060e3c1e90ed5e")
        @NotBlank(message = "Request Id não poder ser nulo ou branco")
        @Size(min = 24, max = 36, message = "Request Id tem que ser igual ou maior a 24 caracteres")
        String requesterId,

        @Schema(description = "Customer Id", example = "664424d1c2060e3c1e90ed5e")
        @NotBlank(message = "Customer Id não poder ser nulo ou branco")
        @Size(min = 24, max = 36, message = "Customer Id tem que ser igual ou maior a 24 caracteres")
        String customerId,

        @Schema(description = "Título da Ordem", example = "Computador")
        @NotBlank(message = "Título não pode ser vazio")
        @Size(min = 3, max = 50, message = "Título deve ter entre 3 e 50 caracteres")
        String title,

        @Schema(description = "Descrição da Ordem", example = "Computador não liga após...")
        @NotBlank(message = "Descrição não pode ser vazio")
        @Size(min = 10, max = 3000, message = "Descrição deve ter entre 10 e 3000 caracteres")
        String description,

        @Schema(description = "Status da Ordem", example = "Open")
        @NotBlank(message = "Status não pode ser vazio")
        @Size(min = 4, max = 15, message = "Status deve ter entre 4 e 15 caracteres")
        String status
) {
}
