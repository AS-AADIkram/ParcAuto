package com.example.parcauto2.Controller;

import com.example.parcauto2.DTO.GrisCardDTO;
import com.example.parcauto2.Service.GrisCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/grisCards")
public class GrisCardController {

    private final GrisCardService grisCardService;

    @Autowired
    public GrisCardController(GrisCardService grisCardService) {
        this.grisCardService = grisCardService;
    }

    @GetMapping
    public ResponseEntity<List<GrisCardDTO>> getAllGrisCards() {
        List<GrisCardDTO> grisCards = grisCardService.getAllGrisCards();
        return ResponseEntity.ok(grisCards);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GrisCardDTO> getGrisCardById(@PathVariable Long id) {
        Optional<GrisCardDTO> grisCardDTO = grisCardService.getGrisCardById(id);
        if (grisCardDTO.isPresent()) {
            return ResponseEntity.ok(grisCardDTO.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<GrisCardDTO> createGrisCard(@RequestBody GrisCardDTO grisCardDTO) {
        GrisCardDTO createdGrisCard = grisCardService.createGrisCard(grisCardDTO);
        return new ResponseEntity<>(createdGrisCard, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGrisCardById(@PathVariable Long id) {
        grisCardService.deleteGrisCardById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/vehicle/{vehicleId}")
    public ResponseEntity<List<GrisCardDTO>> getGrisCardsByVehicleId(@PathVariable Long vehicleId) {
        List<GrisCardDTO> grisCards = grisCardService.getGrisCardsByVehicleId(vehicleId);
        return ResponseEntity.ok(grisCards);
    }

    @PutMapping
    public ResponseEntity<GrisCardDTO> updateGrisCard(@RequestBody GrisCardDTO grisCardDTO) {
        GrisCardDTO updatedGrisCard = grisCardService.updateGrisCard(grisCardDTO);
        if (updatedGrisCard != null) {
            return ResponseEntity.ok(updatedGrisCard);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
