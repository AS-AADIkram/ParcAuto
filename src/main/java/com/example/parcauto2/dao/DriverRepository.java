package com.example.parcauto2.dao;

import com.example.parcauto2.Entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DriverRepository extends CrudRepository<Driver, Long> {
    List<Driver> findByAvailabilityTrue();


    Driver findByName(String driverName);

    List<Driver> findAllByDriverLicenseExpirationDateAfter(LocalDate currentDate);
}
