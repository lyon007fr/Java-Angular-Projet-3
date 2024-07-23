package com.openclassrooms.ApiRentals.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.openclassrooms.ApiRentals.model.Rentals;

@Repository
public interface RentalRepository extends JpaRepository<Rentals, Long> {

}
