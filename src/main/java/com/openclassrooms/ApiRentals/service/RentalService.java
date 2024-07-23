package com.openclassrooms.ApiRentals.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Value;

import com.openclassrooms.ApiRentals.model.Rentals;
import com.openclassrooms.ApiRentals.repository.RentalRepository;


import lombok.Data;

@Data
@Service
public class RentalService {

	 @Value("${upload.path}")
	 private String uploadPath;
	
	@Autowired
	private RentalRepository rentalRepository;
	
	public Iterable<Rentals> getRentals() {
		return rentalRepository.findAll();
	}
	
	public Optional<Rentals> getRental(Long id){
		return rentalRepository.findById(id);
	}
	
	public Rentals saveRental(Rentals rental, MultipartFile file) throws IOException {
		//si le rental n'existe pas je met à jour la date de création
		if (rental.getId() == null) {
			rental.setCreated(LocalDateTime.now());
			}
		rental.setUpdated(LocalDateTime.now());
		
		if (file != null && !file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            Path filePath = Paths.get(uploadPath, fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            String fileUrl = "api/uploads/" +fileName;
            rental.setPicture(fileUrl); // save the file name
        }
		Rentals savedRental = rentalRepository.save(rental);
        return savedRental;
    }

	public List<Rentals> findAll() {
        return rentalRepository.findAll();
    }
	
		
	
}
