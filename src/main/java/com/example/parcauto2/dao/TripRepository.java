package com.example.parcauto2.dao;

import com.example.parcauto2.Entity.Trip;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface TripRepository extends CrudRepository<Trip, Long> {
}
