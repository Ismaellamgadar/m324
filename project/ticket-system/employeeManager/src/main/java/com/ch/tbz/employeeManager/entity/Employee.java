package com.ch.tbz.employeeManager.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Entity
@Getter
@Setter
public class Employee {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "Firstname is mandatory")
    private String firstname;

    @NotBlank(message = "Lastname is mandatory")
    private String lastname;

    @NotNull(message = "Start date is mandatory")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @NotNull(message = "Skill Level is mandatory")
    @Min(value = 1, message = "must be more than or equal to 1")
    @Max(value = 5, message = "must be less than or equal to 5")
    private Integer skillLevel;

    public Employee(String firstname, String lastname, Date startDate, Integer skillLevel) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.startDate = startDate;
        this.skillLevel = skillLevel;
    }

    public Employee() {}
}
