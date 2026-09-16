package com.example.support;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/tickets")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<Ticket> createTicket(@RequestBody Ticket request) {
        Ticket ticket = ticketService.createTicket(request.title(), request.description());

        URI location = URI.create("/tickets/" + ticket.id());
        return ResponseEntity.created(location).body(ticket);
    }

    @GetMapping
    public List<Ticket> listTickets() {
        return ticketService.listTickets();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicket(@PathVariable Long id) {
        Optional<Ticket> ticket = ticketService.findTicket(id);
        if (ticket.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ticket.get());
    }

    @GetMapping("/count")
    public Map<String, Long> countTickets() {
        return Map.of("count", ticketService.countTickets());
    }
}
