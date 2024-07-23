package com.openclassrooms.ApiRentals.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name= "MESSAGES")
public class Messages {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="rental_id")
	private Long rentalid;
	
	@Column(name="user_id")
	private Long owner;
	
	private String message;
	
	@Column(name="created_at")
	private LocalDateTime created;
	
	@Column(name="updated_at")
	private LocalDateTime updated;

}





