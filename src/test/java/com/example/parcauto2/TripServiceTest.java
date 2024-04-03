package com.example.parcauto2;

import com.example.parcauto2.Entity.Trip;
import com.example.parcauto2.Mapper.TripMapper;
import com.example.parcauto2.Service.TripService;
import com.example.parcauto2.dao.TripRepository;
import com.example.parcauto2.DTO.TripDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TripServiceTest {

    @Mock
    private TripRepository tripRepository;

    @Mock
    private TripMapper tripMapper;

    @InjectMocks
    private TripService tripService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllTrips() {
        List<Trip> trips = new ArrayList<>();
        trips.add(new Trip());
        when(tripRepository.findAll()).thenReturn(trips);

        List<TripDTO> tripDTOs = new ArrayList<>();
        tripDTOs.add(new TripDTO());

        when(tripMapper.toDTO(any(Trip.class))).thenReturn(new TripDTO());

        List<TripDTO> result = tripService.getAllTrips();

        assertEquals(tripDTOs.size(), result.size());
        verify(tripRepository, times(1)).findAll();
    }

    @Test
    public void testGetTripById() {
        Long id = 1L;
        Trip trip = new Trip();
        trip.setId(id);
        when(tripRepository.findById(id)).thenReturn(Optional.of(trip));

        TripDTO tripDTO = new TripDTO();
        tripDTO.setId(id);
        when(tripMapper.toDTO(any(Trip.class))).thenReturn(tripDTO);

        TripDTO result = tripService.getTripById(id);

        assertEquals(tripDTO.getId(), result.getId());
        verify(tripRepository, times(1)).findById(id);
    }

    @Test
    public void testSaveTrip() {
        TripDTO tripDTO = new TripDTO();
        tripDTO.setId(1L);
        Trip trip = new Trip();
        trip.setId(1L);

        when(tripMapper.toEntity(tripDTO)).thenReturn(trip);
        when(tripRepository.save(trip)).thenReturn(trip);
        when(tripMapper.toDTO(trip)).thenReturn(tripDTO);

        TripDTO result = tripService.saveTrip(tripDTO);

        assertEquals(tripDTO.getId(), result.getId());
        verify(tripRepository, times(1)).save(trip);
    }

    @Test
    public void testDeleteTripById() {
        Long id = 1L;
        tripService.deleteTripById(id);
        verify(tripRepository, times(1)).deleteById(id);
    }
}
