package com.example.parcauto2.Mapper;

import com.example.parcauto2.DTO.TripDTO;
import com.example.parcauto2.Entity.Trip;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TripMapper {

    @Mapping(source = "driver.id", target = "driverId")
    @Mapping(source = "vehicle.id", target = "vehicleId")
    TripDTO tripToTripDTO(Trip trip);

    @Mapping(source = "driverId", target = "driver.id")
    @Mapping(source = "vehicleId", target = "vehicle.id")
    Trip tripDTOToTrip(TripDTO tripDTO);
    Trip toEntity(TripDTO tripDTO);
    TripDTO toDTO(Trip trip) ;
}
