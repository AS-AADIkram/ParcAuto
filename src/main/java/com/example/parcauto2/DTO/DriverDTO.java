package com.example.parcauto2.DTO;

import lombok.*;

import java.nio.file.FileStore;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DriverDTO {
    private Long id;
    private String name;
    private String surname;
    private String registrationNumber;
    private LocalDate dateOfBirth;
    private String IDCardNumber;
    private boolean availability;
    private List<String> qualifications;
    private List<String> certifications;
    private List<Long> tripIds;
    private Long driverLicenseId;
    private String driverType;



}
