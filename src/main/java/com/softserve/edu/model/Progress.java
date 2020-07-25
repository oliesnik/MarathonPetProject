package com.softserve.edu.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="progress")
public class Progress {

    public enum TaskStatus {
        PASS, FAIL, PENDING
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "DATE")
    private LocalDate started;

    @Column(columnDefinition = "DATE")
    private LocalDate updated;

    @NotNull
    private String status;

    @OneToMany(mappedBy = "progress", cascade = CascadeType.ALL)
    private List<User> trainees;

    @OneToMany(mappedBy = "progress", cascade = CascadeType.ALL)
    private List<Task> tasks;

}
