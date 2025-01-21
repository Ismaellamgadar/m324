package ch.tbz.ticketverwaltung.controller;

import ch.tbz.ticketsystem.dto.TicketUpdateRequestDto;
import ch.tbz.ticketsystem.entity.Ticket;
import ch.tbz.ticketsystem.repository.TicketRepository;
import ch.tbz.ticketsystem.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    TicketRepository ticketrepository;

    @Autowired
    TicketService ticketService;

    @GetMapping()
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable String id) {
        Optional<Ticket> ticket = ticketService.getTicketById(id);
        return ticket.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<Ticket> createTicket(@RequestBody @Validated Ticket ticket) {
        return ResponseEntity.status(ticketService.createTicket(ticket)).body(ticket);
    }

    @PutMapping("/date/{id}")
    private ResponseEntity<Ticket> updateTicketDate(@PathVariable String id, @RequestBody TicketUpdateRequestDto request) {
        LocalDate date = LocalDate.parse(request.getDateString());
        String type = request.getType();
        Optional<Ticket> optionalTicket = ticketService.getTicketById(id);

        if (optionalTicket.isPresent()) {
            Ticket ticket = optionalTicket.get();

            if (Objects.equals(type, "review")) {
                ticket.setReview_date(date);
            } else if (Objects.equals(type, "done")) {
                ticket.setDone_date(date);
            }

            ticketService.updateTicket(ticket);
            return ResponseEntity.status(HttpStatus.OK).body(ticket);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}