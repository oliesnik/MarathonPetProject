package com.softserve.edu.model;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "sprints")
public class Sprint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "DATE")
    private LocalDate finish;

    @Column(columnDefinition = "DATE")
    private LocalDate start;

    @NotNull
    private String title;


}
