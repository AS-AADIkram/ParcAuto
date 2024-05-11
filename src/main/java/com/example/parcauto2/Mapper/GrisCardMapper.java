package com.example.parcauto2.Mapper;

import com.example.parcauto2.DTO.GrisCardDTO;
import com.example.parcauto2.Entity.GrisCard;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface GrisCardMapper {

    @Mapping(source = "vehicle.id", target = "vehicleId")
    GrisCardDTO griscardToGrisCardDTO(GrisCard griscard);

    @Mapping(source = "vehicleId", target = "vehicle.id")
    GrisCard griscardDTOToGrisCard(GrisCardDTO griscardDTO);
}
