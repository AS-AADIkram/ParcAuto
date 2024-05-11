package com.example.parcauto2.Controller;

import com.example.parcauto2.DTO.DriverDTO;
import com.example.parcauto2.Service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/drivers")
public class DriverController {

    private final DriverService driverService;

    @Autowired
    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping
    public ResponseEntity<List<DriverDTO>> getAllDrivers() {
        List<DriverDTO> drivers = driverService.getAllDrivers();
        return ResponseEntity.ok(drivers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DriverDTO> getDriverById(@PathVariable Long id) {
        Optional<DriverDTO> driverDTO = driverService.getDriverById(id);
        if (driverDTO.isPresent()) {
            return ResponseEntity.ok(driverDTO.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<DriverDTO> createDriver(@RequestBody DriverDTO driverDTO) {
        DriverDTO createdDriver = driverService.createDriver(driverDTO);
        return new ResponseEntity<>(createdDriver, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDriverById(@PathVariable Long id) {
        driverService.deleteDriverById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/availability")
    public ResponseEntity<Void> updateDriverAvailability(@PathVariable Long id, @RequestBody boolean availability) {
        driverService.updateDriverAvailability(id, availability);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/validLicense")
    public ResponseEntity<DriverDTO> isDriverWithValidLicense(@PathVariable Long id) {
        Optional<DriverDTO> driverDTO = driverService.isDriverWithValidLicense(id);
        if (driverDTO.isPresent()) {
            return ResponseEntity.ok(driverDTO.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/available")
    public ResponseEntity<List<DriverDTO>> getAvailableDrivers() {
        List<DriverDTO> availableDrivers = driverService.getAvailableDrivers();
        return ResponseEntity.ok(availableDrivers);
    }
    @GetMapping("/validDrivers")
    public ResponseEntity<List<DriverDTO>> getDriversWithValidLicense(
            @RequestParam("tripStart") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate tripStart,
            @RequestParam("tripEnd") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate tripEnd) {
        List<DriverDTO> driversWithValidLicense = driverService.getDriversWithValidLicense(tripStart, tripEnd)
                .stream()
                .map(driverService::convertToDriverDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(driversWithValidLicense);
    }
}
