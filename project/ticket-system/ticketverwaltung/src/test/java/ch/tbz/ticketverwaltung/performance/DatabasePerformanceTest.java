package ch.tbz.ticketverwaltung.performance;

import ch.tbz.ticketverwaltung.entity.Ticket;
import ch.tbz.ticketverwaltung.repository.TicketRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class DatabasePerformanceTest {

    @Autowired
    private TicketRepository ticketRepository;

    @Test
    public void testTicketQueryPerformance() {
        IntStream.range(0, 1000).forEach(i -> {
            Ticket ticket = new Ticket();
            ticket.setTitle("Test Ticket " + i);
            ticket.setDescription("Description for ticket " + i);
            ticketRepository.save(ticket);
        });

        long startTime = System.currentTimeMillis();
        Iterable<Ticket> tickets = ticketRepository.findAll();
        long endTime = System.currentTimeMillis();

        long executionTime = endTime - startTime;
        System.out.println("Abfragezeit für alle Tickets: " + executionTime + "ms");

        assertTrue(executionTime < 2000, "Die Abfrage hat zu lange gedauert!");
    }
}