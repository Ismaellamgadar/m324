package ch.tbz.ticketverwaltung.controller;

import ch.tbz.ticketverwaltung.TicketUpdateRequest;
import ch.tbz.ticketverwaltung.entity.Ticket;
import ch.tbz.ticketverwaltung.repository.TicketRepository;
import ch.tbz.ticketverwaltung.service.TicketService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TicketController.class)
class TicketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TicketRepository ticketRepository;

    @MockBean
    private TicketService ticketService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void testGetAllTickets() throws Exception {
        Mockito.when(ticketRepository.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/ticket"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    void testGetTicketById_Found() throws Exception {
        Ticket ticket = new Ticket();
        ticket.setId(1L);
        Mockito.when(ticketRepository.findById("1")).thenReturn(Optional.of(ticket));

        mockMvc.perform(get("/ticket/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"));
    }

    @Test
    void testGetTicketById_NotFound() throws Exception {
        Mockito.when(ticketRepository.findById("1")).thenReturn(Optional.empty());

        mockMvc.perform(get("/ticket/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateTicket() throws Exception {
        Ticket ticket = new Ticket();
        ticket.setId(1L);
        ticket.setTitle("Test Ticket");

        Mockito.doNothing().when(ticketService).createTicket(any(Ticket.class));

        mockMvc.perform(post("/ticket")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ticket)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.title").value("Test Ticket"));
    }

    @Test
    void testUpdateTicketDate_ReviewDate() throws Exception {
        Ticket ticket = new Ticket();
        ticket.setId(1L);

        Mockito.when(ticketRepository.findById("1")).thenReturn(Optional.of(ticket));
        Mockito.when(ticketRepository.save(any(Ticket.class))).thenReturn(ticket);

        TicketUpdateRequest request = new TicketUpdateRequest();
        request.setDateString("2023-12-01");
        request.setType("review");

        mockMvc.perform(put("/ticket/date/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateTicketDate_NotFound() throws Exception {
        Mockito.when(ticketRepository.findById("1")).thenReturn(Optional.empty());

        TicketUpdateRequest request = new TicketUpdateRequest();
        request.setDateString("2023-12-01");
        request.setType("review");

        mockMvc.perform(put("/ticket/date/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotFound());
    }
}