package com.openclassrooms.ApiRentals.dto;

import java.util.List;

public class RentalListDTO {
    private List<RentalsDTO> rentals;

    // Constructeur
    public RentalListDTO(List<RentalsDTO> rentals) {
        this.rentals = rentals;
    }

    // Getters et Setters
    public List<RentalsDTO> getRentals() {
        return rentals;
    }

    public void setRentals(List<RentalsDTO> rentals) {
        this.rentals = rentals;
    }
}
