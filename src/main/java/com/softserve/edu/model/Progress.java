package com.softserve.edu.model;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"task_id", "trainee_id"})})
public class Progress {

    public enum TaskStatus {
        NEW, PASS, FAIL, PENDING
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "DATE")
    private LocalDate started;

    @Column(columnDefinition = "DATE")
    private LocalDate updated;

    @Enumerated(EnumType.STRING)
    private TaskStatus status = TaskStatus.NEW;;

    @ToString.Exclude
    @ManyToOne
    private Task task;

    @ToString.Exclude
    @ManyToOne
    private User trainee;

}
