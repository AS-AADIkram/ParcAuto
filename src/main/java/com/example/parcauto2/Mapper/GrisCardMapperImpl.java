package com.example.parcauto2.Mapper;

import com.example.parcauto2.DTO.GrisCardDTO;
import com.example.parcauto2.Entity.GrisCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GrisCardMapperImpl implements GrisCardMapper {

    @Autowired
    private VehicleMapper vehicleMapper;

    @Override
    public GrisCardDTO griscardToGrisCardDTO(GrisCard griscard) {
        GrisCardDTO griscardDTO = new GrisCardDTO();
        griscardDTO.setId(griscard.getId());
        griscardDTO.setRegistrationNumber(griscard.getRegistrationNumber());
        griscardDTO.setIssuanceDate(griscard.getIssuanceDate());
        griscardDTO.setExpiryDate(griscard.getExpiryDate());
        griscardDTO.setNumberOfCylinders(griscard.getNumberOfCylinders());
        griscardDTO.setFiscalPower(griscard.getFiscalPower());
        griscardDTO.setNumberOfSeats(griscard.getNumberOfSeats());
        griscardDTO.setVehicleId(griscard.getVehicle() != null ? griscard.getVehicle().getId() : null);
        return griscardDTO;
    }

    @Override
    public GrisCard griscardDTOToGrisCard(GrisCardDTO griscardDTO) {
        GrisCard griscard = new GrisCard();
        griscard.setId(griscardDTO.getId());
        griscard.setRegistrationNumber(griscardDTO.getRegistrationNumber());
        griscard.setIssuanceDate(griscardDTO.getIssuanceDate());
        griscard.setExpiryDate(griscardDTO.getExpiryDate());
        griscard.setNumberOfCylinders(griscardDTO.getNumberOfCylinders());
        griscard.setFiscalPower(griscardDTO.getFiscalPower());
        griscard.setNumberOfSeats(griscardDTO.getNumberOfSeats());
        griscard.setVehicle(griscardDTO.getVehicleId() != null ? vehicleMapper.vehicleIdToVehicle(griscardDTO.getVehicleId()) : null);
        return griscard;
    }
}
