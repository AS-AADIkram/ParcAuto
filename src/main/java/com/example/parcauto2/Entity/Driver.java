package com.example.parcauto2.Entity;



import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;


    private String surname;


    private String registrationNumber;


    private LocalDate dateOfBirth;


    private String IDCardNumber;


    private boolean availability;

    @ElementCollection
    @Column(name = "qualifications")
    private List<String> qualifications;

    @ElementCollection
    @Column(name = "certifications")
    private List<String> certifications;

    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL)
    private List<Trip> trips;

    @OneToOne(mappedBy = "driver", cascade = CascadeType.ALL)
    private driverLicense driverLicense;

    @Enumerated(EnumType.STRING)
    private DriverType driverType;


    public Driver(Long driverId) {
    }
}
