package com.example.support;

import java.time.Instant;

public record Ticket(
        Long id,
        String title,
        String description,
        TicketStatus status,
        Instant createdAt
) {

}
