package com.example.parcauto2.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String vehicleId;


    private String brand;


    private String model;


    private int mileage;


    private boolean availability;


    private String requiredLicence;


    private String specialEquipment;

    @OneToMany(mappedBy = "vehicle")
    private List<Trip> trips;

    @Enumerated(EnumType.STRING)
    private vehicleType vehicleType;


}
