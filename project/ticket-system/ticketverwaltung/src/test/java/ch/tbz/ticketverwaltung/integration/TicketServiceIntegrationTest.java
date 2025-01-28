package ch.tbz.ticketverwaltung.integration;

import ch.tbz.ticketverwaltung.entity.State;
import ch.tbz.ticketverwaltung.entity.Ticket;
import ch.tbz.ticketverwaltung.service.TicketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureMockRestServiceServer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockRestServiceServer
class TicketServiceIntegrationTest {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private RestTemplate restTemplate;

    private MockRestServiceServer mockServer;

    @BeforeEach
    void setUp() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    void testCreateTicket_UserExists() {
        Long userId = 1L;
        Ticket ticket = new Ticket();
        ticket.setUserId(userId);
        ticket.setDescription("Test Description");
        ticket.setTitle("Test Title");
        ticket.setState(State.OPEN);

        String userServiceUrl = "http://localhost:8081/employee/" + userId;

        mockServer.expect(requestTo(userServiceUrl))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("User Found", MediaType.TEXT_PLAIN));

        HttpStatus status = ticketService.createTicket(ticket);

        assertEquals(HttpStatus.CREATED, status);
        mockServer.verify();
    }

    @Test
    void testCreateTicket_UserDoesNotExist() {
        Long userId = 2L;
        Ticket ticket = new Ticket();
        ticket.setUserId(userId);

        String userServiceUrl = "http://localhost:8081/employee/" + userId;

        mockServer.expect(requestTo(userServiceUrl))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.NOT_FOUND));

        HttpStatus status = ticketService.createTicket(ticket);

        assertEquals(HttpStatus.BAD_REQUEST, status);
        mockServer.verify();
    }
}