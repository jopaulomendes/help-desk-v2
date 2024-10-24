package models.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;

public record UpdateOrdeRequest(
        @Schema(description = "Request Id", example = "65b05b898b45aa5cc7c80e88")
        @Size(min = 24, max = 36, message = "Request Id tem que ser igual ou maior a 24 caracteres")
        String requesterId,

        @Schema(description = "Customer Id", example = "65b05b898b45aa5cc7c80e88")
        @Size(min = 24, max = 36, message = "Customer Id tem que ser igual ou maior a 24 caracteres")
        String customerId,

        @Schema(description = "Título da Ordem", example = "Computador")
        @Size(min = 3, max = 50, message = "Título deve ter entre 3 e 50 caracteres")
        String title,

        @Schema(description = "Descrição da Ordem", example = "Computador não liga após...")
        @Size(min = 10, max = 3000, message = "Descrição deve ter entre 10 e 3000 caracteres")
        String description,

        @Schema(description = "Status da Ordem", example = "Open")
        @Size(min = 4, max = 15, message = "Status deve ter entre 4 e 15 caracteres")
        String status
) {
}
