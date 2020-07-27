package com.softserve.edu.model;

import lombok.*;
import org.hibernate.annotations.*;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private LocalDate created;

    @UpdateTimestamp
    private LocalDate updated;

    @NotNull
    private String title;

    @ToString.Exclude
    @NotNull
    @ManyToOne(optional=false)
    @JoinColumn(name="sprint_id")
    private Sprint sprint;

    @OneToMany(mappedBy = "task")
    private List<Progress> progress;
}
