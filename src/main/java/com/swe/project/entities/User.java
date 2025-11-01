package com.swe.project.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.List;


@Data
@NoArgsConstructor
@Entity
@Table(name = "T_USER")
@Inheritance(strategy = InheritanceType.JOINED) // Creates a base T_USER table and separate tables for subclasses
@DiscriminatorColumn(name = "ROLE", discriminatorType = DiscriminatorType.STRING) // This column stores "DOCTOR", "ADMIN", etc.
@DiscriminatorValue("USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Integer id;

    @Column(name = "EMAIL", unique = true, nullable = false)
    private String email;

    @Column(name = "PASSWORD_HASH", nullable = false)
    private String passwordHash;

    @Column(name = "FULL_NAME")
    private String fullName;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "GENDER")
    private String gender;

    @Column(name = "DATE_OF_BIRTH")
    @Temporal(TemporalType.DATE)
    private LocalDate dateOfBirth;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "CREATED_AT", updatable = false, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Patient> patients = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}