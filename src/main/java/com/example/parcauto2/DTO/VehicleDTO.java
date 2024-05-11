package com.example.parcauto2.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleDTO {
    private Long id;
    private String vehicleId;
    private String brand;
    private String model;
    private int mileage;
    private boolean availability;
    private String requiredLicence;
    private String specialEquipment;
}
