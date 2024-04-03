package com.example.parcauto2.Mapper;

import com.example.parcauto2.DTO.TripDTO;
import com.example.parcauto2.Entity.Driver;
import com.example.parcauto2.Entity.Trip;
import com.example.parcauto2.Entity.Vehicle;
import org.springframework.stereotype.Component;

@Component
public class TripMapperImpl implements TripMapper {

    @Override
    public TripDTO tripToTripDTO(Trip trip) {
        if (trip == null) {
            return null;
        }

        TripDTO tripDTO = new TripDTO();
        tripDTO.setId(trip.getId());
        tripDTO.setStartTime(trip.getStartTime());
        tripDTO.setStartLocation(trip.getStartLocation());
        tripDTO.setEndLocation(trip.getEndLocation());
        tripDTO.setDriverId(trip.getDriver() != null ? trip.getDriver().getId() : null);
        tripDTO.setVehicleId(trip.getVehicle() != null ? trip.getVehicle().getId() : null);
        return tripDTO;
    }

    @Override
    public Trip tripDTOToTrip(TripDTO tripDTO) {
        if (tripDTO == null) {
            return null;
        }

        Trip trip = new Trip();
        trip.setId(tripDTO.getId());
        trip.setStartTime(tripDTO.getStartTime());
        trip.setStartLocation(tripDTO.getStartLocation());
        trip.setEndLocation(tripDTO.getEndLocation());
        trip.setDriver(tripDTO.getDriverId() != null ? new Driver(tripDTO.getDriverId()) : null);
        trip.setVehicle(tripDTO.getVehicleId() != null ? new Vehicle(tripDTO.getVehicleId()) : null);
        return trip;
    }

    @Override
    public Trip toEntity(TripDTO tripDTO) {
        if (tripDTO == null) {
            return null;
        }

        Trip trip = new Trip();
        trip.setId(tripDTO.getId());
        trip.setStartTime(tripDTO.getStartTime());
        trip.setStartLocation(tripDTO.getStartLocation());
        trip.setEndLocation(tripDTO.getEndLocation());
        trip.setDriver(tripDTO.getDriverId() != null ? new Driver(tripDTO.getDriverId()) : null);
        trip.setVehicle(tripDTO.getVehicleId() != null ? new Vehicle(tripDTO.getVehicleId()) : null);
        return trip;
    }

    @Override
    public TripDTO toDTO(Trip trip) {
        return tripToTripDTO(trip);
    }
}
