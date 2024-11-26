package ch.tbz.ticketsystem.controllers;

import ch.tbz.ticketsystem.entities.Ticket;
import ch.tbz.ticketsystem.repositories.Ticketrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Ticketcontroller {
    @Autowired Ticketrepository ticketrepository;

    @GetMapping("/")
    public List<Ticket> getAllTickets() {
        return ticketrepository.findAll();
    }

    @PostMapping("/")
    public Long createTicket(String description, Long price, String firstname, String lastname, String event) {
        Ticket newTicket = new Ticket(description, price, firstname, lastname, event);
        return ticketrepository.save(newTicket).getId();
    }
}
