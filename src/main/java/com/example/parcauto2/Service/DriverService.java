package com.example.parcauto2.Service;

import com.example.parcauto2.DTO.DriverDTO;
import com.example.parcauto2.Entity.Driver;
import com.example.parcauto2.Entity.driverLicense;
import com.example.parcauto2.Mapper.DriverMapper;
import com.example.parcauto2.dao.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DriverService {

    private final DriverRepository driverRepository;
    private final DriverMapper driverMapper;

    @Autowired
    public DriverService(DriverRepository driverRepository, DriverMapper driverMapper) {
        this.driverRepository = driverRepository;
        this.driverMapper = driverMapper;
    }

    public List<DriverDTO> getAllDrivers() {
        List<Driver> drivers = (List<Driver>) driverRepository.findAll();
        return drivers.stream()
                .map(driverMapper::driverToDriverDTO)
                .collect(Collectors.toList());
    }

    public Optional<DriverDTO> getDriverById(Long id) {
        Optional<Driver> driver = driverRepository.findById(id);
        return driver.map(driverMapper::driverToDriverDTO);
    }

    public DriverDTO createDriver(DriverDTO driverDTO) {
        Driver driver = driverMapper.driverDTOToDriver(driverDTO);
        Driver savedDriver = driverRepository.save(driver);
        return driverMapper.driverToDriverDTO(savedDriver);
    }

    public void deleteDriverById(Long id) {
        driverRepository.deleteById(id);
    }

    public void updateDriverAvailability(Long id, boolean availability) {
        Optional<Driver> optionalDriver = driverRepository.findById(id);
        optionalDriver.ifPresent(driver -> {
            driver.setAvailability(availability);
            driverRepository.save(driver);
        });
    }



    public Optional<DriverDTO> isDriverWithValidLicense(Long id) {
        Optional<Driver> optionalDriver = driverRepository.findById(id);

        if (optionalDriver.isPresent()) {
            Driver driver = optionalDriver.get();
            driverLicense driverLicense = driver.getDriverLicense();

            if (driverLicense != null) {
                if (driverLicense.getExpirationDate() != null &&
                        !driverLicense.getExpirationDate().isBefore(LocalDate.now())) {
                    return Optional.of(driverMapper.driverToDriverDTO(driver));
                }
            }
        }
        return Optional.empty();
    }


    public boolean isDriverAvailable(Long id) {
        Optional<Driver> optionalDriver = driverRepository.findById(id);
        return optionalDriver.map(Driver::isAvailability).orElse(false);
    }

    public List<DriverDTO> getAvailableDrivers() {
        List<Driver> availableDrivers = driverRepository.findByAvailabilityTrue();
        return availableDrivers.stream()
                .map(driverMapper::driverToDriverDTO)
                .collect(Collectors.toList());
    }
    public DriverDTO convertToDriverDTO(Driver driver) {
        return driverMapper.driverToDriverDTO(driver);
    }



    public List<Driver> getDriversWithValidLicense(LocalDate tripStart, LocalDate tripEnd) {
        List<Driver> driversWithValidLicense = getDriverWithValidLicense();

        return driversWithValidLicense.stream()
                .filter(driver -> isLicenseValidDuringTrip(driver, tripStart, tripEnd))
                .collect(Collectors.toList());
    }

    private List<Driver> getDriverWithValidLicense() {
        LocalDate currentDate = LocalDate.now();
        return driverRepository.findAllByDriverLicenseExpirationDateAfter(currentDate);
    }

    private boolean isLicenseValidDuringTrip(Driver driver, LocalDate tripStart, LocalDate tripEnd) {
        LocalDate expirationDate = driver.getDriverLicense().getExpirationDate();
        return expirationDate.isAfter(tripStart) && expirationDate.isAfter(tripEnd);
    }


}
