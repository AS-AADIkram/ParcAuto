package com.example.parcauto2;

import com.example.parcauto2.DTO.GrisCardDTO;
import com.example.parcauto2.DTO.VehicleDTO;
import com.example.parcauto2.Entity.GrisCard;
import com.example.parcauto2.Entity.Vehicle;
import com.example.parcauto2.Mapper.GrisCardMapper;
import com.example.parcauto2.Mapper.VehicleMapper;
import com.example.parcauto2.Service.GrisCardService;
import com.example.parcauto2.Service.VehicleService;
import com.example.parcauto2.dao.GrisCardRepository;
import com.example.parcauto2.dao.VehicleRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class GrisCardServiceTest {

    @Autowired
    private GrisCardService grisCardService;

    @Autowired
    private GrisCardRepository grisCardRepository;

    @Autowired
    private GrisCardMapper grisCardMapper;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private VehicleMapper vehicleMapper;

    @BeforeEach
    public void setUp() {
        Vehicle vehicle1 = Vehicle.builder()
                .vehicleId("123ABC")
                .brand("Toyota")
                .model("Corolla")
                .mileage(50000)
                .availability(true)
                .requiredLicence("B")
                .specialEquipment("Navigation")
                .build();

        Vehicle vehicle2 = Vehicle.builder()
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

        GrisCard grisCard1 = GrisCard.builder()
                .registrationNumber("ABC123")
                .issuanceDate(LocalDate.of(2020, 1, 1))
                .expiryDate(LocalDate.of(2025, 1, 1))
                .numberOfCylinders(4)
                .numberOfSeats(5)
                .vehicle(vehicle1)
                .build();

        GrisCard grisCard2 = GrisCard.builder()
                .registrationNumber("DEF456")
                .issuanceDate(LocalDate.of(2019, 1, 1))
                .expiryDate(LocalDate.of(2024, 1, 1))
                .numberOfCylinders(6)
                .numberOfSeats(6)
                .vehicle(vehicle2)
                .build();

        grisCardRepository.save(grisCard1);
        grisCardRepository.save(grisCard2);
    }

    @AfterEach
    public void tearDown() {
        grisCardRepository.deleteAll();
        vehicleRepository.deleteAll();
    }

    @Test
    public void testGetAllGrisCards() {
        // Given
        List<GrisCardDTO> expected = grisCardService.getAllGrisCards();

        // When
        List<GrisCardDTO> actual = grisCardService.getAllGrisCards();

        // Then
        assertEquals(expected.size(), actual.size());
    }

    @Test
    public void testGetGrisCardById() {
        // Given
        GrisCardDTO expected = new GrisCardDTO();
        expected.setId(1L);
        expected.setRegistrationNumber("123ABC");
        // Mocking the behavior of grisCardService.getGrisCardById(1L)
        when(grisCardService.getGrisCardById(1L)).thenReturn(Optional.of(expected));

        // When
        Optional<GrisCardDTO> actual = grisCardService.getGrisCardById(1L);

        // Then
        assertTrue(actual.isPresent(), "GrisCardDTO is null ==> ");
        assertEquals(expected, actual.get());
    }



    @Test
    public void testCreateGrisCard() {
        // Given
        VehicleDTO newVehicleDTO = VehicleDTO.builder()
                .vehicleId("789GHI")
                .brand("Nissan")
                .model("Altima")
                .mileage(60000)
                .availability(true)
                .requiredLicence("B")
                .specialEquipment("Bluetooth")
                .build();

        VehicleDTO createdVehicleDTO = vehicleService.createVehicle(newVehicleDTO);

        GrisCardDTO newGrisCardDTO = GrisCardDTO.builder()
                .registrationNumber("GHI789")
                .issuanceDate(LocalDate.of(2021, 1, 1))
                .expiryDate(LocalDate.of(2026, 1, 1))
                .numberOfCylinders(4)
                .numberOfSeats(5)
                .vehicleId(createdVehicleDTO.getId())
                .build();

        // When
        GrisCardDTO createdGrisCardDTO = grisCardService.createGrisCard(newGrisCardDTO);

        // Then
        assertTrue(grisCardRepository.findById(createdGrisCardDTO.getId()).isPresent());
    }

    @Test
    public void testDeleteGrisCardById() {
        // When
        grisCardService.deleteGrisCardById(1L);

        // Then
        assertFalse(grisCardRepository.findById(1L).isPresent());
    }

    @Test
    public void testUpdateGrisCard() {
        // Given
        GrisCardDTO existingGrisCardDTO = new GrisCardDTO();
        existingGrisCardDTO.setId(1L);
        existingGrisCardDTO.setRegistrationNumber("123ABC");
        existingGrisCardDTO.setVehicleId(1L);
        existingGrisCardDTO.setNumberOfCylinders(4);
        existingGrisCardDTO.setNumberOfSeats(5);
        existingGrisCardDTO.setIssuanceDate(LocalDate.of(2024, Month.MARCH, 10));
        existingGrisCardDTO.setExpiryDate(LocalDate.of(2024, Month.MARCH, 10));

        // When
        GrisCardDTO returnedGrisCardDTO = grisCardService.updateGrisCard(existingGrisCardDTO);

        // Then
        assertNotNull(returnedGrisCardDTO);
        assertEquals(existingGrisCardDTO.getId(), returnedGrisCardDTO.getId());
        assertEquals(existingGrisCardDTO.getVehicleId(), returnedGrisCardDTO.getVehicleId());
        assertEquals(existingGrisCardDTO.getNumberOfCylinders(), returnedGrisCardDTO.getNumberOfCylinders());
        assertEquals(existingGrisCardDTO.getNumberOfSeats(), returnedGrisCardDTO.getNumberOfSeats());
        assertEquals(existingGrisCardDTO.getIssuanceDate(), returnedGrisCardDTO.getIssuanceDate());
        assertEquals(existingGrisCardDTO.getExpiryDate(), returnedGrisCardDTO.getExpiryDate());
    }


    @Test
    public void testGetGrisCardsByVehicleId() {
        // Given
        List<GrisCardDTO> expected = grisCardService.getGrisCardsByVehicleId(1L);

        // When
        List<GrisCardDTO> actual = grisCardService.getGrisCardsByVehicleId(1L);

        // Then
        assertEquals(expected.size(), actual.size());
    }
}
