package com.openclassrooms.ApiRentals.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.ApiRentals.model.Rentals;
import com.openclassrooms.ApiRentals.repository.RentalRepository;


import lombok.Data;

@Data
@Service
public class RentalService {

	@Autowired
	private RentalRepository rentalRepository;
	
	public Iterable<Rentals> getRentals() {
		return rentalRepository.findAll();
	}
	
	public Optional<Rentals> getRental(final Long id){
		return rentalRepository.findById(id);
	}
	
	public Rentals saveRental(Rentals rental) {
		//si le rental n'existe pas je met à jour la date de création
		if (rental.getId() == null) {
			rental.setCreated(LocalDateTime.now());
			}
		rental.setUpdated(LocalDateTime.now());
		Rentals savedRental = rentalRepository.save(rental);
        return savedRental;
    }

	public List<Rentals> findAll() {
        return rentalRepository.findAll();
    }
	
		
	
}
