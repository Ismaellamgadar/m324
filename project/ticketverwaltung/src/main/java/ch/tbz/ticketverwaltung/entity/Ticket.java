package ch.tbz.ticketverwaltung.entity;

import ch.tbz.ticketverwaltung.State;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity
@ToString
@Getter
@Setter
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotNull(message = "State is mandatory")
    @Pattern(regexp = "OPEN|IN_PROGRESS|REVIEW|DONE", message = "State must be one of these options: 'OPEN', 'IN_PROGRESS', 'REVIEW', 'DONE'")
    private State state;
    @NotNull(message = "Title is mandatory")
    private String title;

    @NotNull(message = "Description is mandatory")
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate review_date;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate done_date;

    @NotNull(message = "UserId is mandatory")
    private Long userId;

    public Ticket(State state, String title, String description, LocalDate review_date, LocalDate done_date, Long userId) {
        this.state = state;
        this.description = description;
        this.title = title;
        this.review_date = review_date;
        this.done_date = done_date;
        this.userId = userId;
    }

    public Ticket() {}
}
