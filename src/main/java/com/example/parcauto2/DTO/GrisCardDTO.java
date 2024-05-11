package com.example.parcauto2.DTO;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GrisCardDTO {
    private Long id;
    private String registrationNumber;
    private LocalDate issuanceDate;
    private LocalDate expiryDate;
    private int numberOfCylinders;
    private int fiscalPower;
    private int numberOfSeats;
    private Long vehicleId;
}
