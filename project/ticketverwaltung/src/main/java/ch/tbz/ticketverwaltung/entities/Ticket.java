package ch.tbz.ticketverwaltung.entities;

import ch.tbz.ticketverwaltung.enums.State;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@ToString
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(nullable = false)
    private State state;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate review_date;
    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate done_date;
    @Column(nullable = false)
    private String firstname;

    public Ticket(State state, String title, String description, LocalDate review_date, LocalDate done_date, String firstname) {
        this.state = state;
        this.description = description;
        this.title = title;
        this.review_date = review_date;
        this.done_date = done_date;
        this.firstname = firstname;
    }

    public Ticket() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public State getState() { return state; }
    public void setState(State state) { this.state = state; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDate getReview_date() { return review_date; }
    public void setReview_date(LocalDate review_date) { this.review_date = review_date; }

    public LocalDate getDone_date() { return done_date; }
    public void setDone_date(LocalDate done_date) { this.done_date = done_date; }

    public String getFirstname() { return firstname; }
    public void setFirstname(String firstname) { this.firstname = firstname; }
}
