package com.example.parcauto2.dao;
import com.example.parcauto2.Entity.Vehicle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface VehicleRepository extends CrudRepository<Vehicle, Long> {

}
