package com.example.parcauto2.Mapper;

import com.example.parcauto2.DTO.DriverDTO;
import com.example.parcauto2.Entity.Driver;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DriverMapper {

    @Mapping(source = "driverLicense.id", target = "driverLicenseId")
    @Mapping(source = "driverType.name", target = "driverType")
    DriverDTO driverToDriverDTO(Driver driver);

    @Mapping(source = "driverLicenseId", target = "driverLicense.id")
    @Mapping(source = "driverType", target = "driverType.name")
    Driver driverDTOToDriver(DriverDTO driverDTO);
}
