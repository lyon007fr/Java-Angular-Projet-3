package com.openclassrooms.ApiRentals.Response;

import java.util.List;

import com.openclassrooms.ApiRentals.dto.RentalsDTO;

public class RentalsResponse { //Constructeur pour initialiser la liste de RentalsDTO
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