package com.openclassrooms.ApiRentals.dto;

import lombok.Data;

@Data
public class MessagesDTO {
    private Long rentalId;
    private Long ownerId;
    private String message;
}
	

