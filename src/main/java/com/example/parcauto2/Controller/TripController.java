package com.example.parcauto2.Controller;

import com.example.parcauto2.DTO.TripDTO;
import com.example.parcauto2.Service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/trips")
public class TripController {

    private final TripService tripService;

    @Autowired
    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @PostMapping
    public ResponseEntity<TripDTO> createTrip(@Valid @RequestBody TripDTO tripDTO) {
        TripDTO createdTrip = tripService.createTrip(tripDTO);
        return new ResponseEntity<>(createdTrip, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TripDTO>> getAllTrips() {
        List<TripDTO> trips = tripService.getAllTrips();
        return ResponseEntity.ok(trips);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TripDTO> getTripById(@PathVariable Long id) {
        TripDTO tripDTO = tripService.getTripById(id);
        if (tripDTO != null) {
            return ResponseEntity.ok(tripDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TripDTO> updateTrip(@PathVariable Long id, @Valid @RequestBody TripDTO tripDTO) {
        tripDTO.setId(id);  // Set the ID in the DTO for update
        TripDTO updatedTrip = tripService.saveTrip(tripDTO);
        if (updatedTrip != null) {
            return ResponseEntity.ok(updatedTrip);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTripById(@PathVariable Long id) {
        tripService.deleteTripById(id);
        return ResponseEntity.noContent().build();
    }
}
