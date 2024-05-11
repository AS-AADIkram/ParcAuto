package com.example.parcauto2.Entity;


import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TechnicalVisit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public static final int minAgeByYears = 5;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;


}
