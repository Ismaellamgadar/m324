package ch.tbz.ticketsystem.performance;

import ch.tbz.ticketsystem.entity.Ticket;
import ch.tbz.ticketsystem.repository.TicketRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY) // Nutzt die H2-Datenbank für Tests
public class DatabasePerformanceTest {

    @Autowired
    private TicketRepository ticketRepository;

    @Test
    public void testTicketQueryPerformance() {
        // Erzeuge Testdaten
        IntStream.range(0, 1000).forEach(i -> {
            Ticket ticket = new Ticket();
            ticket.setTitle("Test Ticket " + i);
            ticket.setDescription("Description for ticket " + i);
            ticketRepository.save(ticket);
        });

        // Messe die Performance der Abfrage
        long startTime = System.currentTimeMillis();
        Iterable<Ticket> tickets = ticketRepository.findAll();
        long endTime = System.currentTimeMillis();

        long executionTime = endTime - startTime;
        System.out.println("Abfragezeit für alle Tickets: " + executionTime + "ms");

        // Sicherstellen, dass die Abfrage schnell genug ist
        assertTrue(executionTime < 2000, "Die Abfrage hat zu lange gedauert!");
    }
}