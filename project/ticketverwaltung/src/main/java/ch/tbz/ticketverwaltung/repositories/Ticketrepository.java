package ch.tbz.ticketverwaltung.repositories;

import ch.tbz.ticketverwaltung.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Ticketrepository extends JpaRepository<Ticket, String> {

}
