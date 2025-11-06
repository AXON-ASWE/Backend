package com.swe.project.entities.users;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "T_USER")
@Builder
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Integer userId;

    @Column(nullable = false, unique = true, name = "EMAIL")
    private String email;

    @Column(nullable = false, name = "PASSWORD_HASH")
    private String passwordHash;

    @Column(name = "FULL_NAME", nullable = false)
    private String fullName;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "GENDER")
    private String gender;

    @Column(name = "DATE_OF_BIRTH")
    private LocalDate dateOfBirth;

    @Column(name = "ROLE", nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleName role;

    @Column(name = "STATUS", nullable = false)
    private String status;

    @Column(nullable = false, updatable = false, name = "CREATED_AT")
    @Builder.Default
    private LocalDate createdAt = LocalDate.now();

}
