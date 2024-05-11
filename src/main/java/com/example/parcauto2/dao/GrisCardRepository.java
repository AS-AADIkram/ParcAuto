package com.example.parcauto2.dao;

import com.example.parcauto2.Entity.Driver;
import com.example.parcauto2.Entity.GrisCard;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrisCardRepository extends CrudRepository<GrisCard, Long> {
    List<GrisCard> findByVehicleId(Long vehicleId);
}
