package com.example.parcauto2;

import com.example.parcauto2.DTO.DriverDTO;
import com.example.parcauto2.Entity.Driver;
import com.example.parcauto2.Entity.driverLicense;
import com.example.parcauto2.Entity.licenseType;
import com.example.parcauto2.Mapper.DriverMapper;
import com.example.parcauto2.Service.DriverService;
import com.example.parcauto2.dao.DriverRepository;
import com.example.parcauto2.dao.driverLicenseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
public class DriverServiceTest {

    @Autowired
    private DriverService driverService;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private DriverMapper driverMapper;

    @Autowired
    private driverLicenseRepository driverLicenseRepository;



    @Test
    public void testGetDriverById() {
        // Given
        Driver driver = driverRepository.save(Driver.builder().name("John Doe").build());

        // When
        Optional<DriverDTO> driverDTOOptional = driverService.getDriverById(driver.getId());

        // Then
        assertTrue(driverDTOOptional.isPresent());
        assertEquals(driver.getId(), driverDTOOptional.get().getId());
    }

    @Test
    public void testCreateDriver() {
        // Given
        DriverDTO driverDTO = DriverDTO.builder().name("John Doe").build();

        // When
        DriverDTO createdDriver = driverService.createDriver(driverDTO);

        // Then
        assertEquals(driverDTO.getName(), createdDriver.getName());
        assertTrue(createdDriver.getId() != null);
    }

    @Test
    public void testDeleteDriverById() {
        // Given
        Driver driver = driverRepository.save(Driver.builder().name("John Doe").build());

        // When
        driverService.deleteDriverById(driver.getId());

        // Then
        assertTrue(driverRepository.findById(driver.getId()).isEmpty());
    }

    @Test
    public void testUpdateDriverAvailability() {
        // Given
        Driver driver = driverRepository.save(Driver.builder().name("John Doe").build());

        // When
        driverService.updateDriverAvailability(driver.getId(), true);

        // Then
        assertTrue(driverRepository.findById(driver.getId()).get().isAvailability());
    }

    @Test
    public void testIsDriverWithValidLicense() {
        // Given
        com.example.parcauto2.Entity.driverLicense driverLicenseEntity = com.example.parcauto2.Entity.driverLicense.builder()
                .licenseNumber("12345")
                .issueDate(LocalDate.of(2020, 1, 1))
                .expirationDate(LocalDate.of(2022, 1, 1)) // Licence expirée
                .type("Type")
                .licenseType(licenseType.A)
                .build();

        driverLicenseEntity = driverLicenseRepository.save(driverLicenseEntity);

        com.example.parcauto2.Entity.Driver driver = com.example.parcauto2.Entity.Driver.builder()
                .name("John Doe")
                .driverLicense(driverLicenseEntity)
                .build();

        driver = driverRepository.save(driver);

        // When
        Optional<DriverDTO> driverDTOOptional = driverService.isDriverWithValidLicense(driver.getId());

        // Then
        assertFalse(driverDTOOptional.isPresent()); // La licence du conducteur est expirée
    }




    @Test
    public void testIsDriverAvailable() {
        // Given
        Driver driver = driverRepository.save(Driver.builder().name("John Doe").availability(true).build());

        // When
        boolean isAvailable = driverService.isDriverAvailable(driver.getId());

        // Then
        assertTrue(isAvailable);
    }

    @Test
    public void testGetAvailableDrivers() {
        // Given
        driverRepository.save(Driver.builder().name("John Doe").availability(true).build());
        driverRepository.save(Driver.builder().name("Jane Smith").availability(false).build());

        // When
        List<DriverDTO> availableDrivers = driverService.getAvailableDrivers();

        // Then
        assertEquals(1, availableDrivers.size());
        assertTrue(availableDrivers.get(0).isAvailability());
    }
}
