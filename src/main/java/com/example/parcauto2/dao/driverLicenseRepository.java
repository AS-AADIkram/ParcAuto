package com.example.parcauto2.dao;
import com.example.parcauto2.Entity.driverLicense;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface driverLicenseRepository extends CrudRepository<driverLicense, Long> {

}
