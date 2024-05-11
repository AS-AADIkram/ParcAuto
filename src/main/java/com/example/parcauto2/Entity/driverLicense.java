package com.example.parcauto2.Entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class driverLicense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String licenseNumber;


    private LocalDate issueDate;

    private LocalDate expirationDate;

    private String type;

    @OneToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @Enumerated(EnumType.STRING)
    private licenseType licenseType;
}
