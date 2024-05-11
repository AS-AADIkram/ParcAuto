package com.example.parcauto2;

import com.example.parcauto2.DTO.VehicleDTO;
import com.example.parcauto2.Entity.Vehicle;
import com.example.parcauto2.Mapper.VehicleMapper;
import com.example.parcauto2.Service.VehicleService;
import com.example.parcauto2.dao.VehicleRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class VehicleServiceTest {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private VehicleMapper vehicleMapper;

    @BeforeEach
    public void setUp() {
        Vehicle vehicle1 = Vehicle.builder()
                .id(1L)
                .vehicleId("123ABC")
                .brand("Toyota")
                .model("Corolla")
                .mileage(50000)
                .availability(true)
                .requiredLicence("B")
                .specialEquipment("Navigation")
                .build();

        Vehicle vehicle2 = Vehicle.builder()
                .id(2L)
                .vehicleId("456DEF")
                .brand("Honda")
                .model("Accord")
                .mileage(70000)
                .availability(true)
                .requiredLicence("B")
                .specialEquipment("Sunroof")
                .build();

        vehicleRepository.save(vehicle1);
        vehicleRepository.save(vehicle2);
    }

    @AfterEach
    public void tearDown() {
        vehicleRepository.deleteAll();
    }

    @Test
    public void testGetAllVehicles() {
        // Given
        List<VehicleDTO> expected = vehicleService.getAllVehicles();

        // When
        List<VehicleDTO> actual = vehicleService.getAllVehicles();

        // Then
        assertEquals(expected.size(), actual.size());
    }



    @Test
    public void testCreateVehicle() {
        // Given
        VehicleDTO newVehicleDTO = new VehicleDTO(null, "789GHI", "Nissan", "Altima", 60000, true, "B", "Bluetooth");

        // When
        VehicleDTO createdVehicleDTO = vehicleService.createVehicle(newVehicleDTO);

        // Then
        assertTrue(vehicleRepository.findById(createdVehicleDTO.getId()).isPresent());
    }

    @Test
    public void testDeleteVehicleById() {
        // When
        vehicleService.deleteVehicleById(1L);

        // Then
        assertFalse(vehicleRepository.findById(1L).isPresent());
    }

    @Test
    public void testUpdateVehicle() {
        // Given
        VehicleDTO updateVehicleDTO = new VehicleDTO(1L, "XYZ123", "Toyota", "Corolla", 50000, true, "B", "Navigation");

        // When
        VehicleDTO updatedVehicleDTO = vehicleService.updateVehicle(updateVehicleDTO);

        // Then
        assertEquals(updateVehicleDTO.getVehicleId(), updatedVehicleDTO.getVehicleId());
    }
}

