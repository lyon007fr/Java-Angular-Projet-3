package com.openclassrooms.ApiRentals.controller;


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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.openclassrooms.ApiRentals.model.Rentals;
import com.openclassrooms.ApiRentals.dto.ApiResponse;
import com.openclassrooms.ApiRentals.dto.RentalsDTO;
import com.openclassrooms.ApiRentals.service.RentalService;

@RestController
@RequestMapping("/api")
public class RentalController {
	
	@Autowired
	private RentalService rentalService;
	
	public class RentalsMapper {

	    public static RentalsDTO toDTO(Rentals rental) {
	        RentalsDTO dto = new RentalsDTO();
	        dto.setId(rental.getId());
	        dto.setName(rental.getName());
	        dto.setSurface(rental.getSurface());
	        dto.setPrice(rental.getPrice());
	        dto.setPicture(rental.getPicture());
	        dto.setDescription(rental.getDescription());
	        dto.setOwner_id(rental.getOwner_id());
	        dto.setCreated(rental.getCreated());
	        dto.setUpdated(rental.getUpdated());
	        return dto;
	    }

	    public static List<RentalsDTO> toDTOList(Iterable<Rentals> rentals) {
	        List<RentalsDTO> dtoList = new ArrayList<>();
	        for (Rentals rental : rentals) {
	            dtoList.add(toDTO(rental));
	        }
	        return dtoList;
	    }
	}
	
	 @GetMapping("/rentals")
	    public ResponseEntity<RentalsResponse> allRentals() {
	        Iterable<Rentals> rentals = rentalService.getRentals();
	        List<RentalsDTO> rentalDTOs = RentalsMapper.toDTOList(rentals);
	        RentalsResponse response = new RentalsResponse(rentalDTOs);
	        return ResponseEntity.ok(response);
	    }

	    // Classe interne pour envelopper la réponse
	 
	    public static class RentalsResponse { //Constructeur pour initialiser la liste de RentalsDTO
	        private List<RentalsDTO> rentals;

	        public RentalsResponse(List<RentalsDTO> rentals) {
	            this.rentals = rentals;
	        }

	     // Getter pour accéder à la liste de RentalsDTO
	        public List<RentalsDTO> getRentals() {
	            return rentals;
	        }

	     // Setter pour modifier la liste de RentalsDTO
	        public void setRentals(List<RentalsDTO> rentals) {
	            this.rentals = rentals;
	        }
	    }
    
    /**
     * Methode pour convertir un object rental en DTO
     * @param rental
     * @return rentalDTO
     */
    private RentalsDTO convertToDTO(Rentals rental) {
        RentalsDTO rentalDTO = new RentalsDTO();
        rentalDTO.setId(rental.getId());
        rentalDTO.setName(rental.getName());
        rentalDTO.setSurface(rental.getSurface());
        rentalDTO.setPrice(rental.getPrice());
        rentalDTO.setPicture(rental.getPicture());
        rentalDTO.setDescription(rental.getDescription());
        rentalDTO.setOwner_id(rental.getOwner_id());
        rentalDTO.setCreated(rental.getCreated());
        rentalDTO.setUpdated(rental.getUpdated());
        return rentalDTO;
    }
    
    /**
     * Methode pour convertir des objects rental en list de DTO
     * @param iterable rental
     * @return list rentalDTO
     */
    

	
    @PostMapping("/rentals")
    public ResponseEntity<ApiResponse> newRentals(@RequestBody RentalsDTO newRentalDTO) {
    	
    	Rentals newRental = convertToEntity(newRentalDTO);
        rentalService.saveRental(newRental);
        ApiResponse response = new ApiResponse("Rental created !");
        return ResponseEntity.ok(response);
    }
	
	//update one rental
    @PutMapping("/rentals/{id}")
    public ResponseEntity<ApiResponse> replaceRental(@RequestBody RentalsDTO newRentalDTO, @PathVariable Long id) {
        Optional<Rentals> optionalRental = rentalService.getRental(id);

        if (optionalRental.isPresent()) { //si le rental existe alors je mets à jour le rental avec les données du DTO
            Rentals rental = optionalRental.get();
            rental.setName(newRentalDTO.getName());
            rental.setSurface(newRentalDTO.getSurface());
            rental.setPrice(newRentalDTO.getPrice());
            rental.setDescription(newRentalDTO.getDescription());
            rental.setOwner_id(newRentalDTO.getOwner_id());
            rental.setPicture(newRentalDTO.getPicture());

            rentalService.saveRental(rental);
                        
            ApiResponse responseRentalUpdated = new ApiResponse("Rental updated !"); //retourne le message dans le json retourne
            
            return ResponseEntity.ok(responseRentalUpdated);
        } else {
            Rentals newRental = convertToEntity(newRentalDTO);
            newRental.setCreated(LocalDateTime.now());
            newRental.setUpdated(LocalDateTime.now());

            rentalService.saveRental(newRental);
            
            ApiResponse responseCreated = new ApiResponse("Rental created !");

            return ResponseEntity.ok(responseCreated);
        }
    }

    /**
     * convertit un DTO en entity
     * @param rentalDTO
     * @return rental entity
     */
    private Rentals convertToEntity(RentalsDTO rentalDTO) {
        Rentals rental = new Rentals();
        rental.setName(rentalDTO.getName());
        rental.setSurface(rentalDTO.getSurface());
        rental.setPrice(rentalDTO.getPrice());
        rental.setDescription(rentalDTO.getDescription());
        rental.setOwner_id(rentalDTO.getOwner_id());
        rental.setPicture(rentalDTO.getPicture());
        rental.setCreated(rentalDTO.getCreated());
        rental.setUpdated(rentalDTO.getUpdated());
        return rental;
    }
	

	
	//get rental by id
	@GetMapping("/rentals/{id}")
	public ResponseEntity<RentalsDTO> one(@PathVariable Long id) {
	    Optional<Rentals> optionalRental = rentalService.getRental(id);

	    if (optionalRental.isPresent()) {
	        Rentals rental = optionalRental.get();
	        RentalsDTO rentalDTO = convertToDTO(rental);
	        return ResponseEntity.ok(rentalDTO);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	
}
