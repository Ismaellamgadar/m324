package ch.tbz.ticketverwaltung.service;

import ch.tbz.ticketverwaltung.State;
import ch.tbz.ticketverwaltung.entity.Ticket;
import ch.tbz.ticketverwaltung.repository.TicketRepository;
import ch.tbz.ticketverwaltung.service.TicketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TicketServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private TicketService ticketService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTicket_UserExists() {
        Ticket ticket = new Ticket();
        ticket.setDescription("");
        ticket.setState(State.OPEN);
        ticket.setUserId(1L);
        ticket.setTitle("Test Ticket");

        String userServiceUrl = "http://localhost:8080/employee/1";

        when(restTemplate.getForEntity(userServiceUrl, Boolean.class))
                .thenReturn(ResponseEntity.ok(true));

        ticketService.createTicket(ticket);

        ArgumentCaptor<Ticket> captor = ArgumentCaptor.forClass(Ticket.class);
        verify(ticketRepository, times(1)).save(captor.capture());
        Ticket savedTicket = captor.getValue();
        assertEquals(1L, savedTicket.getUserId());
        assertEquals("Test Ticket", savedTicket.getTitle());
    }

    @Test
    void testCreateTicket_UserDoesNotExist() {
        Ticket ticket = new Ticket();
        ticket.setDescription("");
        ticket.setTitle("");
        ticket.setState(State.OPEN);
        ticket.setUserId(1L);

        String userServiceUrl = "http://localhost:8080/employee/1";

        when(restTemplate.getForEntity(userServiceUrl, Boolean.class))
                .thenReturn(ResponseEntity.ok(false));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            ticketService.createTicket(ticket);
        });

        assertEquals("User does not exist", exception.getMessage());
        verify(ticketRepository, never()).save(any(Ticket.class));
    }

    @Test
    void testCreateTicket_NullResponseFromUserService() {
        Ticket ticket = new Ticket();
        ticket.setDescription("");
        ticket.setTitle("");
        ticket.setState(State.OPEN);
        ticket.setUserId(1L);

        String userServiceUrl = "http://localhost:8080/employee/1";

        when(restTemplate.getForEntity(userServiceUrl, Boolean.class))
                .thenReturn(ResponseEntity.ok(null));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            ticketService.createTicket(ticket);
        });

        assertEquals("User does not exist", exception.getMessage());
        verify(ticketRepository, never()).save(any(Ticket.class));
    }
}
