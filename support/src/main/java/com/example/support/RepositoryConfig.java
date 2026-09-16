package com.example.support;

import java.time.Instant;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class RepositoryConfig {

    @Bean
    @Primary
    public TicketRepository jpaTicketRepository(TicketEntityRepository entityRepository) {
        return new JpaTicketRepository(entityRepository);
    }

    @Bean
    public TicketRepository demoTicketRepository() {
        InMemoryTicketRepository repository = new InMemoryTicketRepository();
        repository.save(new Ticket(
                null,
                "VPN disconnects every few minutes",
                "Since the client update on Monday, the VPN drops the connection roughly every ten minutes.",
                TicketStatus.OPEN,
                Instant.now()
        ));
        repository.save(new Ticket(
                null,
                "Password reset email never arrives",
                "I requested a reset link three times, but nothing shows up in my inbox or spam folder.",
                TicketStatus.IN_PROGRESS,
                Instant.now()
        ));
        repository.save(new Ticket(
                null,
                "Can't upload attachments larger than 5 MB",
                "Uploading a 7 MB PDF to a ticket fails with a generic error message.",
                TicketStatus.OPEN,
                Instant.now()
        ));
        return repository;
    }
}
