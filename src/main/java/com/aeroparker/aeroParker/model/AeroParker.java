package com.aeroparker.aeroParker.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class AeroParker {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private LocalDateTime registered = LocalDateTime.now();

    @Column(nullable = false)
    private String emailAddress;

    @Column(nullable = false, length = 5)
    private String title;

    @Column(nullable = false, length = 50)
    private String firstName;

    @Column(nullable = false, length = 50)
    private String lastName;

    @Column(nullable = false)
    private String addressLine1;

    @Column
    private String addressLine2;

    @Column
    private String city;

    @Column(nullable = false, length = 10)
    private String postcode;

    @Column(length = 20)
    private String phoneNumber;
}
