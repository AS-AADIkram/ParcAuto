package com.example.parcauto2.Service;

import com.example.parcauto2.DTO.GrisCardDTO;
import com.example.parcauto2.Entity.GrisCard;
import com.example.parcauto2.Mapper.GrisCardMapper;
import com.example.parcauto2.dao.GrisCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GrisCardService {

    private final GrisCardRepository grisCardRepository;
    private final GrisCardMapper grisCardMapper;

    @Autowired
    public GrisCardService(GrisCardRepository grisCardRepository, GrisCardMapper grisCardMapper) {
        this.grisCardRepository = grisCardRepository;
        this.grisCardMapper = grisCardMapper;
    }

    public List<GrisCardDTO> getAllGrisCards() {
        List<GrisCard> grisCards = (List<GrisCard>) grisCardRepository.findAll();
        return grisCards.stream()
                .map(grisCardMapper::griscardToGrisCardDTO)
                .collect(Collectors.toList());
    }

    public Optional<GrisCardDTO> getGrisCardById(Long id) {
        Optional<GrisCard> grisCard = grisCardRepository.findById(id);
        return grisCard.map(grisCardMapper::griscardToGrisCardDTO);
    }

    public GrisCardDTO createGrisCard(GrisCardDTO grisCardDTO) {
        GrisCard grisCard = grisCardMapper.griscardDTOToGrisCard(grisCardDTO);
        GrisCard savedGrisCard = grisCardRepository.save(grisCard);
        return grisCardMapper.griscardToGrisCardDTO(savedGrisCard);
    }

    public void deleteGrisCardById(Long id) {
        grisCardRepository.deleteById(id);
    }

    public List<GrisCardDTO> getGrisCardsByVehicleId(Long vehicleId) {
        List<GrisCard> grisCards = grisCardRepository.findByVehicleId(vehicleId);
        return grisCards.stream()
                .map(grisCardMapper::griscardToGrisCardDTO)
                .collect(Collectors.toList());
    }

    public GrisCardDTO updateGrisCard(GrisCardDTO grisCardDTO) {
        GrisCard existingGrisCard = grisCardRepository.findById(grisCardDTO.getId()).orElse(null);
        if (existingGrisCard == null) {
            return null;
        }

        existingGrisCard.setRegistrationNumber(grisCardDTO.getRegistrationNumber());
        existingGrisCard.setIssuanceDate(grisCardDTO.getIssuanceDate());
        existingGrisCard.setExpiryDate(grisCardDTO.getExpiryDate());
        existingGrisCard.setNumberOfCylinders(grisCardDTO.getNumberOfCylinders());
        existingGrisCard.setFiscalPower(grisCardDTO.getFiscalPower());
        existingGrisCard.setNumberOfSeats(grisCardDTO.getNumberOfSeats());

        GrisCard updatedGrisCard = grisCardRepository.save(existingGrisCard);
        return grisCardMapper.griscardToGrisCardDTO(updatedGrisCard);
    }
}
