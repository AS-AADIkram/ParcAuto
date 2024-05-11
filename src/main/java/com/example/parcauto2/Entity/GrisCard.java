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
public class GrisCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String registrationNumber; //immatriculation

    @Temporal(TemporalType.DATE)
    private LocalDate issuanceDate; //MC_date

    @Temporal(TemporalType.DATE)
    private LocalDate expiryDate;  //fin de validity

    private int numberOfCylinders;

    private int fiscalPower;

    private int numberOfSeats;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;
}
