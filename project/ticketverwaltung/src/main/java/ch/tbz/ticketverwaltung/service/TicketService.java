package ch.tbz.ticketverwaltung.service;

import ch.tbz.ticketverwaltung.UserResponse;
import ch.tbz.ticketverwaltung.entity.Ticket;
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
        String userServiceUrl = "http://localhost:8081/employee/" + ticket.getUserId();
        ResponseEntity<String> response = restTemplate.getForEntity(userServiceUrl, String.class);
        System.out.println("Response from User Service: " + response.getBody());

        ticketRepository.save(ticket);
    }

}
