package ch.tbz.ticketverwaltung.controller;

import ch.tbz.ticketverwaltung.TicketUpdateRequest;
import ch.tbz.ticketverwaltung.entity.Ticket;
import ch.tbz.ticketverwaltung.repository.TicketRepository;
import ch.tbz.ticketverwaltung.service.TicketService;
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
public class TicketController {
    @Autowired
    TicketRepository ticketrepository;

    @Autowired
    TicketService ticketService;

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
        ticketService.createTicket(ticket);
        return ResponseEntity.status(HttpStatus.CREATED).body(ticket);
    }

    @PutMapping("/date/{id}")
    private ResponseEntity<Ticket> updateTicketDate(@PathVariable String id, @RequestBody TicketUpdateRequest request) {
        LocalDate date = LocalDate.parse(request.getDateString());
        String type = request.getType();
        Optional<Ticket> optionalTicket = ticketrepository.findById(id);

        if (optionalTicket.isPresent()) {
            Ticket ticket = optionalTicket.get();

            if (Objects.equals(type, "review")) {
                ticket.setReview_date(date);
            } else if (Objects.equals(type, "done")) {
                ticket.setDone_date(date);
            }

            ticketrepository.save(ticket);
            return ResponseEntity.status(HttpStatus.OK).body(ticket);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
