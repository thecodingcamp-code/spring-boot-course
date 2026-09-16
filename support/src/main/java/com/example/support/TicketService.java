package com.example.support;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;

    public TicketService(@Qualifier("demoTicketRepository") TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public List<Ticket> listTickets() {
        return ticketRepository.findAll();
    }

    public Optional<Ticket> findTicket(Long id) {
        return ticketRepository.findById(id);
    }

    public Ticket createTicket(String title, String description) {
        Ticket ticket = new Ticket(
                null,
                title,
                description,
                TicketStatus.OPEN,
                Instant.now()
        );
        return ticketRepository.save(ticket);
    }

    public long countTickets() {
        return ticketRepository.count();
    }

}
