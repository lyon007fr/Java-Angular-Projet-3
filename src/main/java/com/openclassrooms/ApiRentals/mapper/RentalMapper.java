package com.openclassrooms.ApiRentals.mapper;

import java.util.ArrayList;
import java.util.List;
import com.openclassrooms.ApiRentals.dto.RentalsDTO;
import com.openclassrooms.ApiRentals.model.Rentals;

public class RentalMapper {
	
    public static RentalsDTO toDTO(Rentals rental) {
        RentalsDTO dto = new RentalsDTO();
        dto.setId(rental.getId());
        dto.setName(rental.getName());
        dto.setSurface(rental.getSurface());
        dto.setPrice(rental.getPrice());
        dto.setPicture(rental.getPicture());
        dto.setDescription(rental.getDescription());
        dto.setOwner_id(rental.getOwner_id());
        dto.setCreated_at(rental.getCreated());
        dto.setUpdated_at(rental.getUpdated());
        return dto;
    }

    public static List<RentalsDTO> toDTOList(Iterable<Rentals> rentals) {
        List<RentalsDTO> dtoList = new ArrayList<>();
        for (Rentals rental : rentals) {
            dtoList.add(toDTO(rental));
        }
        return dtoList;
    }
    
    /**
     * Methode pour convertir un object rental en DTO
     * @param rental
     * @return rentalDTO
     */
    public static RentalsDTO convertToDTO(Rentals rental) {
        RentalsDTO rentalDTO = new RentalsDTO();
        rentalDTO.setId(rental.getId());
        rentalDTO.setName(rental.getName());
        rentalDTO.setSurface(rental.getSurface());
        rentalDTO.setPrice(rental.getPrice());
        rentalDTO.setPicture(rental.getPicture());
        rentalDTO.setDescription(rental.getDescription());
        rentalDTO.setOwner_id(rental.getOwner_id());
        rentalDTO.setCreated_at(rental.getCreated());
        rentalDTO.setUpdated_at(rental.getUpdated());
        return rentalDTO;
    }

    /**
     * convertit un DTO en entity
     * @param rentalDTO
     * @return rental entity
     */
    public static Rentals convertToEntity(RentalsDTO rentalDTO) {
        Rentals rental = new Rentals();
        rental.setName(rentalDTO.getName());
        rental.setSurface(rentalDTO.getSurface());
        rental.setPrice(rentalDTO.getPrice());
        rental.setDescription(rentalDTO.getDescription());
        rental.setOwner_id(rentalDTO.getOwner_id());
        rental.setPicture(rentalDTO.getPicture());
        rental.setCreated(rentalDTO.getCreated_at());
        rental.setUpdated(rentalDTO.getUpdated_at());
        return rental;
    }
    
}
