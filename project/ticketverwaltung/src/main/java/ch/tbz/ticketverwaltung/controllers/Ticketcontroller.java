package ch.tbz.ticketverwaltung.controllers;

import ch.tbz.ticketverwaltung.entities.Ticket;
import ch.tbz.ticketverwaltung.enums.State;
import ch.tbz.ticketverwaltung.repositories.Ticketrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
public class Ticketcontroller {
    @Autowired Ticketrepository ticketrepository;

    @GetMapping("/")
    public List<Ticket> getAllTickets() {
        return ticketrepository.findAll();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable String id) {
        Optional<Ticket> ticket = ticketrepository.findById(id);
        return ticket.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public ResponseEntity<Ticket> createTicket(@RequestBody @Validated Ticket ticket) {
        Ticket savedTicket = ticketrepository.save(ticket);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedTicket);
    }
}
