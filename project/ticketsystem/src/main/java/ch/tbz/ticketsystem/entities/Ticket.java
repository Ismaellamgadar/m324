package ch.tbz.ticketsystem.entities;

import jakarta.persistence.*;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    String description;
    Long price;
    String firstname;
    String lastname;
    String event;

    public Ticket(String description, Long price, String firstname, String lastname, String event) {
        this.description = description;
        this.price = price;
        this.firstname = firstname;
        this.lastname = lastname;
        this.event = event;
    }

    public Ticket() {}

    public Long getId() {
        return id;
    }
}
