package com.example.parcauto2.Mapper;

import com.example.parcauto2.DTO.VehicleDTO;
import com.example.parcauto2.Entity.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VehicleMapper {

    VehicleDTO vehicleToVehicleDTO(Vehicle vehicle);

    Vehicle vehicleDTOToVehicle(VehicleDTO vehicleDTO);

    @Mapping(target = "id", source = "vehicleId")
    Vehicle vehicleIdToVehicle(Long vehicleId);
}
