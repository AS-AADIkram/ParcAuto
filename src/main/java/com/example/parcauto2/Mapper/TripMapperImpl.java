package com.example.parcauto2.Mapper;

import com.example.parcauto2.DTO.TripDTO;
import com.example.parcauto2.Entity.Driver;
import com.example.parcauto2.Entity.Trip;
import com.example.parcauto2.Entity.Vehicle;
import com.example.parcauto2.dao.DriverRepository;
import com.example.parcauto2.dao.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TripMapperImpl implements TripMapper {

    private final DriverRepository driverRepository;
    private final VehicleRepository vehicleRepository;

    @Autowired
    public TripMapperImpl(DriverRepository driverRepository, VehicleRepository vehicleRepository) {
        this.driverRepository = driverRepository;
        this.vehicleRepository = vehicleRepository;
    }
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
        tripDTO.setDeparture(trip.getDeparture());
        tripDTO.setDestination(trip.getDestination());
        tripDTO.setDepartureDate(trip.getDepartureDate());
        tripDTO.setDepartureTime(trip.getDepartureTime());
        tripDTO.setArrivalDate(trip.getArrivalDate());
        tripDTO.setArrivalTime(trip.getArrivalTime());
        tripDTO.setNumberOfPassengers(trip.getNumberOfPassengers());
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
        trip.setDeparture(tripDTO.getDeparture());
        trip.setDestination(tripDTO.getDestination());
        trip.setDepartureDate(tripDTO.getDepartureDate());
        trip.setDepartureTime(tripDTO.getDepartureTime());
        trip.setArrivalDate(tripDTO.getArrivalDate());
        trip.setArrivalTime(tripDTO.getArrivalTime());
        trip.setNumberOfPassengers(tripDTO.getNumberOfPassengers());
        // Load driver and vehicle from the database using their IDs

        Driver driver = new Driver();
        driver.setId(tripDTO.getDriverId());
        Vehicle vehicle = new Vehicle();
        vehicle.setId(tripDTO.getVehicleId());


        trip.setDriver(tripDTO.getDriverId() != null ? driver : null);
        trip.setVehicle(tripDTO.getVehicleId() != null ? vehicle : null);
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
        // Charger le conducteur et le véhicule à partir de la base de données en utilisant leurs IDs
        trip.setDriver(tripDTO.getDriverId() != null ? driverRepository.findById(tripDTO.getDriverId()).orElse(null) : null);
        trip.setVehicle(tripDTO.getVehicleId() != null ? vehicleRepository.findById(tripDTO.getVehicleId()).orElse(null) : null);
        return trip;
    }

    @Override
    public TripDTO toDTO(Trip trip) {
        return tripToTripDTO(trip);
    }
}
