package com.softserve.edu.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="users")
public class User {

    public enum Role {
        MENTOR, TRAINEE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 2, max = 20, message = "First name must be between 2 and 20 characters")
    private String firstName;

    @NotNull
    @Size(min = 2, max = 20, message = "Last name must be between 2 and 20 characters")
    private String lastName;

    @Column(unique = true)
    @NotNull
    @Pattern(regexp = ".+@.+\\..+", message = "Please provide a valid email address!")
    private String email;

    @NotNull
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "trainee")
    private List<Progress> progresses;

    @ToString.Exclude
    @ManyToMany(mappedBy = "users")
    private List<Marathon> marathons;
}
