package ch.tbz.ticketverwaltung.repository;

import ch.tbz.ticketverwaltung.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, String> {

}
