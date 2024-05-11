package com.example.parcauto2.Mapper;

import com.example.parcauto2.DTO.VehicleDTO;
import com.example.parcauto2.Entity.Vehicle;
import org.springframework.stereotype.Component;

@Component
public class VehicleMapperImpl implements VehicleMapper {

    @Override
    public VehicleDTO vehicleToVehicleDTO(Vehicle vehicle) {
        if (vehicle == null) {
            return null;
        }

        VehicleDTO vehicleDTO = new VehicleDTO();
        vehicleDTO.setId(vehicle.getId());
        vehicleDTO.setVehicleId(vehicle.getVehicleId());
        vehicleDTO.setBrand(vehicle.getBrand());
        vehicleDTO.setModel(vehicle.getModel());
        vehicleDTO.setMileage(vehicle.getMileage());
        vehicleDTO.setAvailability(vehicle.isAvailability());
        vehicleDTO.setRequiredLicence(vehicle.getRequiredLicence());
        vehicleDTO.setSpecialEquipment(vehicle.getSpecialEquipment());

        return vehicleDTO;
    }

    @Override
    public Vehicle vehicleDTOToVehicle(VehicleDTO vehicleDTO) {
        if (vehicleDTO == null) {
            return null;
        }

        Vehicle vehicle = new Vehicle();
        vehicle.setId(vehicleDTO.getId());
        vehicle.setVehicleId(vehicleDTO.getVehicleId());
        vehicle.setBrand(vehicleDTO.getBrand());
        vehicle.setModel(vehicleDTO.getModel());
        vehicle.setMileage(vehicleDTO.getMileage());
        vehicle.setAvailability(vehicleDTO.isAvailability());
        vehicle.setRequiredLicence(vehicleDTO.getRequiredLicence());
        vehicle.setSpecialEquipment(vehicleDTO.getSpecialEquipment());

        return vehicle;
    }
    @Override
    public Vehicle vehicleIdToVehicle(Long vehicleId) {
        if (vehicleId == null) {
            return null;
        }
        Vehicle vehicle = new Vehicle();
        vehicle.setId(vehicleId);
        return vehicle;
    }
}
