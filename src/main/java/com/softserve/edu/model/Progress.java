package com.softserve.edu.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
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


    private LocalDate started;
    private String status;
    private LocalDate updated;




}
