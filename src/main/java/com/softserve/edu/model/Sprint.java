package com.softserve.edu.model;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
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

    @OneToMany(mappedBy = "sprint")
    private List<Task> tasks;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name="marathon_id", nullable=false)
    private Marathon marathon;
}
