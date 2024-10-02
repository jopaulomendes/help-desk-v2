package models.response;

public record OrderResponse(
        String id,
        String requesterId,
        String customerId,
        String title,
        String description,
        String status,
        String createdAt,
        String closedAt
) {
}
