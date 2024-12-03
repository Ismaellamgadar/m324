package ch.tbz.ticketverwaltung.service;

import ch.tbz.ticketverwaltung.entitiy.Ticket;
import ch.tbz.ticketverwaltung.repository.TicketRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TicketService {
    private final RestTemplate restTemplate;
    private final TicketRepository ticketRepository;

    public TicketService(RestTemplate restTemplate, TicketRepository ticketRepository) {
        this.restTemplate = restTemplate;
        this.ticketRepository = ticketRepository;
    }

    public void createTicket(Ticket ticket) {
        String userServiceUrl = "http://localhost:8080/employee/" + ticket.getUserId();
        ResponseEntity<Boolean> response = restTemplate.getForEntity(userServiceUrl, Boolean.class);

        if (response.getBody() == null || !response.getBody()) {
            throw new IllegalArgumentException("User does not exist");
        }

        ticketRepository.save(ticket);
    }

}
