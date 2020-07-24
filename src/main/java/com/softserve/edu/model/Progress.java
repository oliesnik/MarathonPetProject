package com.softserve.edu.model;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Progress {

    public enum TaskStatus{
        PASS, FAIL, PENDING
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Task task;

    @Temporal(TemporalType.DATE)
    private LocalDate started;

    @Temporal(TemporalType.DATE)
    private LocalDate updated;

    @NotNull
    private String status;




}
