package com.example.parcauto2.Service;

import com.example.parcauto2.DTO.VehicleDTO;
import com.example.parcauto2.Entity.Vehicle;
import com.example.parcauto2.Mapper.VehicleMapper;
import com.example.parcauto2.dao.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.ArrayList;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final VehicleMapper vehicleMapper;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository, VehicleMapper vehicleMapper) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleMapper = vehicleMapper;
    }

    public List<VehicleDTO> getAllVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        vehicleRepository.findAll().forEach(vehicles::add);
        return vehicles.stream()
                .map(vehicleMapper::vehicleToVehicleDTO)
                .collect(Collectors.toList());
    }

    public Optional<VehicleDTO> getVehicleById(Long id) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);
        return vehicle.map(vehicleMapper::vehicleToVehicleDTO);
    }

    public VehicleDTO createVehicle(VehicleDTO vehicleDTO) {
        Vehicle vehicle = vehicleMapper.vehicleDTOToVehicle(vehicleDTO);
        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        return vehicleMapper.vehicleToVehicleDTO(savedVehicle);
    }

    public void deleteVehicleById(Long id) {
        vehicleRepository.deleteById(id);
    }

    public VehicleDTO updateVehicle(VehicleDTO vehicleDTO) {
        Vehicle vehicle = vehicleMapper.vehicleDTOToVehicle(vehicleDTO);
        Vehicle updatedVehicle = vehicleRepository.save(vehicle);
        return vehicleMapper.vehicleToVehicleDTO(updatedVehicle);
    }
}
