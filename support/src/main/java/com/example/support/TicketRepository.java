package com.example.support;

import java.util.List;
import java.util.Optional;

public interface TicketRepository {
    Ticket save(Ticket ticket);
    List<Ticket> findAll();
    Optional<Ticket> findById(Long id);
    long count();
}
