package com.openclassrooms.ApiRentals.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.openclassrooms.ApiRentals.model.Messages;


public interface MessageRepository extends JpaRepository<Messages, Long> {

}
