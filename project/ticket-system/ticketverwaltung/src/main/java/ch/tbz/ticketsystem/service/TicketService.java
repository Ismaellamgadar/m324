package ch.tbz.ticketsystem.service;

import ch.tbz.ticketsystem.entity.Ticket;
import ch.tbz.ticketsystem.repository.TicketRepository;
import ch.tbz.ticketsystem.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    public TicketService(TicketRepository ticketRepository, UserRepository userRepository) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
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
        boolean userExists = userRepository.doesUserExist(ticket.getUserId());

        if(userExists){
            ticketRepository.save(ticket);
            return HttpStatus.CREATED;
        } else {
            return HttpStatus.BAD_REQUEST;
        }
    }

}
