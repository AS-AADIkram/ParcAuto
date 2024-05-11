package com.example.parcauto2;

import com.example.parcauto2.Entity.Trip;
import com.example.parcauto2.Mapper.TripMapper;
import com.example.parcauto2.Service.TripService;
import com.example.parcauto2.dao.TripRepository;
import com.example.parcauto2.DTO.TripDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class TripServiceTest {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private TripMapper tripMapper;

    @Autowired
    private TripService tripService;

    @Test
    public void testGetAllTrips() {
        // Given
        Trip trip1 = Trip.builder()
                .StartLocation("Start 1")
                .EndLocation("End 1")
                .build();

        Trip trip2 = Trip.builder()
                .StartLocation("Start 2")
                .EndLocation("End 2")
                .build();

        tripRepository.save(trip1);
        tripRepository.save(trip2);

        // When
        List<TripDTO> tripDTOList = tripService.getAllTrips();

        // Then
        assertNotNull(tripDTOList);
        assertEquals(2, tripDTOList.size());
        assertEquals("Start 1", tripDTOList.get(0).getStartLocation());
        assertEquals("End 1", tripDTOList.get(0).getEndLocation());
        assertEquals("Start 2", tripDTOList.get(1).getStartLocation());
        assertEquals("End 2", tripDTOList.get(1).getEndLocation());
    }

    @Test
    public void testGetTripById() {
        // Given
        Trip trip = Trip.builder()
                .StartLocation("Start")
                .EndLocation("End")
                .build();
        trip = tripRepository.save(trip);

        // When
        TripDTO tripDTO = tripService.getTripById(trip.getId());

        // Then
        assertNotNull(tripDTO);
        assertEquals("Start", tripDTO.getStartLocation());
        assertEquals("End", tripDTO.getEndLocation());
    }

    @Test
    public void testSaveTrip() {
        // Given
        TripDTO tripDTO = new TripDTO();
        tripDTO.setStartLocation("Start");
        tripDTO.setEndLocation("End");

        // When
        TripDTO savedTripDTO = tripService.saveTrip(tripDTO);

        // Then
        assertNotNull(savedTripDTO);
        assertNotNull(savedTripDTO.getId());
        assertEquals("Start", savedTripDTO.getStartLocation());
        assertEquals("End", savedTripDTO.getEndLocation());

        Trip trip = tripRepository.findById(savedTripDTO.getId()).orElse(null);
        assertNotNull(trip);
        assertEquals("Start", trip.getStartLocation());
        assertEquals("End", trip.getEndLocation());
    }

    @Test
    public void testDeleteTripById() {
        // Given
        Trip trip = Trip.builder()
                .StartLocation("Start")
                .EndLocation("End")
                .build();
        trip = tripRepository.save(trip);

        // When
        tripService.deleteTripById(trip.getId());

        // Then
        Trip deletedTrip = tripRepository.findById(trip.getId()).orElse(null);
        assertNull(deletedTrip);
    }

    @Test
    public void testCreateTrip() {
        // Given
        TripDTO tripDTO = new TripDTO();
        tripDTO.setStartLocation("Start");
        tripDTO.setEndLocation("End");

        // When
        TripDTO savedTripDTO = tripService.createTrip(tripDTO);

        // Then
        assertNotNull(savedTripDTO);
        assertNotNull(savedTripDTO.getId());
        assertEquals("Start", savedTripDTO.getStartLocation());
        assertEquals("End", savedTripDTO.getEndLocation());

        Trip trip = tripRepository.findById(savedTripDTO.getId()).orElse(null);
        assertNotNull(trip);
        assertEquals("Start", trip.getStartLocation());
        assertEquals("End", trip.getEndLocation());
    }
}
