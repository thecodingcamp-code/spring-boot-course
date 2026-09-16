package com.example.support;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;

public class JpaTicketRepository implements TicketRepository {

    private final TicketEntityRepository entityRepository;

    public JpaTicketRepository(TicketEntityRepository entityRepository) {
        this.entityRepository = entityRepository;
    }

    @Override
    public Ticket save(Ticket ticket) {
        TicketEntity saved = entityRepository.save(toEntity(ticket));
        return toTicket(saved);
    }

    @Override
    public List<Ticket> findAll() {
        return entityRepository.findAll(Sort.by("id")).stream()
                .map(this::toTicket)
                .toList();
    }

    @Override
    public Optional<Ticket> findById(Long id) {
        return entityRepository.findById(id)
                .map(this::toTicket);
    }

    @Override
    public long count() {
        return entityRepository.count();
    }

    private TicketEntity toEntity(Ticket ticket) {
        return new TicketEntity(
                ticket.id(),
                ticket.title(),
                ticket.description(),
                ticket.status(),
                ticket.createdAt()
        );
    }

    private Ticket toTicket(TicketEntity entity) {
        return new Ticket(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getStatus(),
                entity.getCreatedAt()
        );
    }
}
