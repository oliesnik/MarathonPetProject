package com.softserve.edu.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "DATE")
    private LocalDate created;

    @Column(columnDefinition = "DATE")
    private LocalDate updated;

    @NotNull
    private String title;

    @ManyToOne(optional = false)
    @JoinColumn(name = "sprint_id", referencedColumnName = "id")
    private Sprint sprint;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private List<Progress> progress;
}
