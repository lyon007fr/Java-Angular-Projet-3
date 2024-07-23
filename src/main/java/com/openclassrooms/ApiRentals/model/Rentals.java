package com.openclassrooms.ApiRentals.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name= "RENTALS")
public class Rentals {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private Long surface;
	
	private Integer price;
	
	private String picture;
	
	private String description;
	
	//@Column(name="owner_id")
	private Long owner_id;
	
	@JsonFormat(pattern="yyyy/MM/dd")
	@Column(name="created_at")
	private LocalDateTime created;
	
	@JsonFormat(pattern="yyyy/MM/dd")
	@Column(name="updated_at")
	private LocalDateTime updated;

}
