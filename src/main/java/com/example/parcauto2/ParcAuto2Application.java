package com.example.parcauto2;

import com.example.parcauto2.Entity.Trip;
import com.example.parcauto2.dao.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class ParcAuto2Application implements CommandLineRunner {

    @Autowired
    TripRepository tripRepository;

    @Override
    public void run(String... args) throws Exception {
        Trip trip = Trip.builder()
                .departure("Agadir").build();
        tripRepository.save(trip);

    }

    public static void main(String[] args) {
        SpringApplication.run(ParcAuto2Application.class, args);
    }

}
