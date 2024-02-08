package models.response;

import lombok.Builder;

@Builder
public record AuthenticateResponse(
        String token,
        String type
) {
}
