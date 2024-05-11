package com.example.parcauto2.Service;

import com.example.parcauto2.Entity.Trip;
import com.example.parcauto2.DTO.TripDTO;
import com.example.parcauto2.Mapper.TripMapper;
import com.example.parcauto2.dao.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TripService {

    private final TripRepository tripRepository;
    private final TripMapper tripMapper;


    @Autowired
    public TripService(TripRepository tripRepository, TripMapper tripMapper) {
        this.tripRepository = tripRepository;
        this.tripMapper = tripMapper;
    }
    public TripDTO createTrip(TripDTO tripDTO) {
        Trip trip = tripMapper.toEntity(tripDTO);
        Trip savedTrip = tripRepository.save(trip);
        return tripMapper.toDTO(savedTrip);
    }

    public List<TripDTO> getAllTrips() {
        Iterable<Trip> tripIterable = tripRepository.findAll();
        return StreamSupport.stream(tripIterable.spliterator(), false)
                .map(tripMapper::toDTO)
                .collect(Collectors.toList());
    }

    public TripDTO getTripById(Long id) {
        return tripRepository.findById(id)
                .map(tripMapper::toDTO)
                .orElse(null);
    }

    public TripDTO saveTrip(TripDTO tripDTO) {
        Trip trip = tripMapper.toEntity(tripDTO);
        Trip savedTrip = tripRepository.save(trip);
        return tripMapper.toDTO(savedTrip);
    }

    public void deleteTripById(Long id) {
        tripRepository.deleteById(id);
    }


}
