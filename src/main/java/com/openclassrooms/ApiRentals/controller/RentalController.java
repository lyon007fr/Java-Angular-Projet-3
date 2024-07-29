package com.openclassrooms.ApiRentals.controller;


import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.openclassrooms.ApiRentals.model.Rentals;
import com.openclassrooms.ApiRentals.Response.RentalsResponse;
import com.openclassrooms.ApiRentals.dto.ApiResponse;
import com.openclassrooms.ApiRentals.dto.RentalsDTO;
import com.openclassrooms.ApiRentals.mapper.RentalMapper;
import com.openclassrooms.ApiRentals.service.RentalService;

@RestController
@RequestMapping("/api")
public class RentalController {
	
	@Autowired
	private RentalService rentalService;
	

	
	 @GetMapping("/rentals")
	    public ResponseEntity<RentalsResponse> allRentals() {
	        Iterable<Rentals> rentals = rentalService.getRentals();
	        List<RentalsDTO> rentalDTOs = RentalMapper.toDTOList(rentals);
	        RentalsResponse response = new RentalsResponse(rentalDTOs);
	        return ResponseEntity.ok(response);
	    }
	    
      

    @PostMapping(value = "/rentals", consumes = {"multipart/form-data"})
    public ResponseEntity<ApiResponse> newRentals(
            @RequestParam("name") String name,
            @RequestParam("surface") Long surface,
            @RequestParam("price") Integer price,
            @RequestParam("description") String description,
            @RequestParam("picture") MultipartFile file) {

        RentalsDTO newRentalDTO = new RentalsDTO();
        newRentalDTO.setName(name);
        newRentalDTO.setSurface(surface);
        newRentalDTO.setPrice(price);
        newRentalDTO.setDescription(description);

        Rentals newRental = RentalMapper.convertToEntity(newRentalDTO);
        try {
            rentalService.saveRental(newRental, file);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(new ApiResponse("Error uploading file"));
        }
        ApiResponse response = new ApiResponse("Rental created!");
        return ResponseEntity.ok(response);
    }
	
	//update one rental
    @PutMapping("/rentals/{id}")
    public ResponseEntity<ApiResponse> replaceRental(
            @RequestPart("rental") RentalsDTO newRentalDTO,
            @RequestPart("file") MultipartFile file,
            @PathVariable Long id) {

        Optional<Rentals> optionalRental = rentalService.getRental(id);

        if (optionalRental.isPresent()) {
            Rentals rental = optionalRental.get();
            rental.setName(newRentalDTO.getName());
            rental.setSurface(newRentalDTO.getSurface());
            rental.setPrice(newRentalDTO.getPrice());
            rental.setDescription(newRentalDTO.getDescription());
            rental.setOwner_id(newRentalDTO.getOwner_id());
            rental.setPicture(newRentalDTO.getPicture());

            try {
                rentalService.saveRental(rental, file);
            } catch (IOException e) {
                return ResponseEntity.status(500).body(new ApiResponse("Error uploading file"));
            }

            ApiResponse responseRentalUpdated = new ApiResponse("Rental updated!");
            return ResponseEntity.ok(responseRentalUpdated);
        } else {
            Rentals newRental = RentalMapper.convertToEntity(newRentalDTO);
            newRental.setCreated(LocalDateTime.now());
            newRental.setUpdated(LocalDateTime.now());

            try {
                rentalService.saveRental(newRental, file);
            } catch (IOException e) {
                return ResponseEntity.status(500).body(new ApiResponse("Error uploading file"));
            }

            ApiResponse responseCreated = new ApiResponse("Rental created!");
            return ResponseEntity.ok(responseCreated);
        }
    }

 	
	//get rental by id
	@GetMapping("/rentals/{id}")
	public ResponseEntity<RentalsDTO> one(@PathVariable Long id) {
	    Optional<Rentals> optionalRental = rentalService.getRental(id);

	    if (optionalRental.isPresent()) {
	        Rentals rental = optionalRental.get();
	        RentalsDTO rentalDTO = RentalMapper.convertToDTO(rental);
	        return ResponseEntity.ok(rentalDTO);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	
}
