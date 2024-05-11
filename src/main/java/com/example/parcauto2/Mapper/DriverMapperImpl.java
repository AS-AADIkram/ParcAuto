package com.example.parcauto2.Mapper;

import com.example.parcauto2.DTO.DriverDTO;
import com.example.parcauto2.Entity.Driver;
import org.springframework.stereotype.Component;

@Component
public class DriverMapperImpl implements DriverMapper {

    @Override
    public DriverDTO driverToDriverDTO(Driver driver) {
        if (driver == null) {
            return null;
        }

        DriverDTO driverDTO = new DriverDTO();
        driverDTO.setId(driver.getId());
        driverDTO.setName(driver.getName());
        driverDTO.setSurname(driver.getSurname());
        driverDTO.setRegistrationNumber(driver.getRegistrationNumber());
        driverDTO.setDateOfBirth(driver.getDateOfBirth());
        driverDTO.setIDCardNumber(driver.getIDCardNumber());
        driverDTO.setAvailability(driver.isAvailability());
        driverDTO.setQualifications(driver.getQualifications());
        driverDTO.setCertifications(driver.getCertifications());
        driverDTO.setDriverLicenseId(driver.getDriverLicense() != null ? driver.getDriverLicense().getId() : null);

        return driverDTO;
    }

    @Override
    public Driver driverDTOToDriver(DriverDTO driverDTO) {
        if (driverDTO == null) {
            return null;
        }

        Driver driver = new Driver();
        driver.setId(driverDTO.getId());
        driver.setName(driverDTO.getName());
        driver.setSurname(driverDTO.getSurname());
        driver.setRegistrationNumber(driverDTO.getRegistrationNumber());
        driver.setDateOfBirth(driverDTO.getDateOfBirth());
        driver.setIDCardNumber(driverDTO.getIDCardNumber());
        driver.setAvailability(driverDTO.isAvailability());
        driver.setQualifications(driverDTO.getQualifications());
        driver.setCertifications(driverDTO.getCertifications());
        // Assuming driverLicense and driverType are already mapped in driverDTO
        // You may need to implement conversion logic for these fields if they are not
        return driver;
    }
}
