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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
    void testGetAllTickets() {
        // Arrange
        List<Ticket> tickets = Arrays.asList(
                new Ticket(),
                new Ticket()
        );
        tickets.get(0).setTitle("Ticket 1");
        when(ticketRepository.findAll()).thenReturn(tickets);

        List<Ticket> result = ticketService.getAllTickets();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Ticket 1", result.get(0).getTitle());
        verify(ticketRepository, times(1)).findAll();
    }

    @Test
    void testGetTicketById_TicketExists() {
        Long ticketId = 1L;
        Ticket ticket = new Ticket();
        ticket.setId(ticketId);
        ticket.setTitle("Ticket 1");
        when(ticketRepository.findById(ticketId.toString())).thenReturn(Optional.of(ticket));

        Optional<Ticket> result = ticketService.getTicketById(ticketId.toString());

        assertTrue(result.isPresent());
        assertEquals(ticketId, result.get().getId());
        assertEquals("Ticket 1", result.get().getTitle());
        verify(ticketRepository, times(1)).findById(ticketId.toString());
    }

    @Test
    void testGetTicketById_TicketDoesNotExist() {
        String ticketId = "999";
        when(ticketRepository.findById(ticketId)).thenReturn(Optional.empty());

        Optional<Ticket> result = ticketService.getTicketById(ticketId);

        assertFalse(result.isPresent());
        verify(ticketRepository, times(1)).findById(ticketId);
    }

    @Test
    void testUpdateTicket() {
        Ticket ticket = new Ticket();
        ticket.setTitle("Updated Ticket");
        when(ticketRepository.save(ticket)).thenReturn(ticket);

        Ticket result = ticketService.updateTicket(ticket);

        assertNotNull(result);
        assertEquals("Updated Ticket", result.getTitle());
        verify(ticketRepository, times(1)).save(ticket);
    }

    @Test
    void testCreateTicket_UserExists() {
        Ticket ticket = new Ticket();
        ticket.setDescription("");
        ticket.setState(State.OPEN);
        ticket.setUserId(1L);
        ticket.setTitle("Test Ticket");

        String userServiceUrl = "http://localhost:8081/employee/1";

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
        ticket.setUserId(100L);

        String userServiceUrl = "http://localhost:8081/employee/1";

        when(restTemplate.getForEntity(userServiceUrl, Boolean.class))
                .thenReturn(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null));

        HttpStatus status = ticketService.createTicket(ticket);

        assertEquals(HttpStatus.BAD_REQUEST, status);
        verify(ticketRepository, never()).save(any(Ticket.class));
    }
}
