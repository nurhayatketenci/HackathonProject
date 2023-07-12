package com.study.expensetracking.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;
    private String username;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String email;

    private String password;

    @OneToMany(mappedBy = "user")
    private List<Expense> expenses;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<Role> roles;


}
