package ch.tbz.ticketsystem.repositories;

import ch.tbz.ticketsystem.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Ticketrepository extends JpaRepository<Ticket, String> {

}
