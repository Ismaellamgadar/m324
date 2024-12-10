package ch.tbz.ticketverwaltung.service;

import ch.tbz.ticketverwaltung.entity.Ticket;
import ch.tbz.ticketverwaltung.repository.TicketRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }
    public List<Ticket> getAllTickets() {
        return this.ticketRepository.findAll();
    }

    public Optional<Ticket> getTicketById(String id) {
        return this.ticketRepository.findById(id);
    }

    public Ticket updateTicket(Ticket ticket) {
        return this.ticketRepository.save(ticket);
    }

    public HttpStatus createTicket(Ticket ticket) {
        String userServiceUrl = "http://localhost:8081/employee/" + ticket.getUserId();

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(userServiceUrl))
                .GET()
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                ticketRepository.save(ticket);
                return HttpStatus.CREATED;
            } else {
                return HttpStatus.BAD_REQUEST;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return HttpStatus.OK;
    }

}
