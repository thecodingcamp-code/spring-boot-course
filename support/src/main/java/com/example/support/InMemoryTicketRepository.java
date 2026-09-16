package com.example.support;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryTicketRepository implements TicketRepository {
    private final Map<Long, Ticket> tickets = new ConcurrentHashMap<>();
    private final AtomicLong nextId = new AtomicLong(1);

    @Override
    public Ticket save(Ticket ticket) {
        Ticket toStore = ticket;
        if (ticket.id() == null) {
            toStore = new Ticket(
                    nextId.getAndIncrement(),
                    ticket.title(),
                    ticket.description(),
                    ticket.status(),
                    ticket.createdAt()
            );
        }
        tickets.put(toStore.id(), toStore);
        return toStore;
    }

    @Override
    public List<Ticket> findAll() {
        return tickets.values().stream()
                .sorted(Comparator.comparing(Ticket::id))
                .toList();
    }

    @Override
    public Optional<Ticket> findById(Long id) {
        return Optional.ofNullable(tickets.get(id));
    }

    @Override
    public long count() {
        return tickets.size();
    }
}
