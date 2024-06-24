package com.openclassrooms.ApiRentals.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.ApiRentals.dto.ApiResponse;
import com.openclassrooms.ApiRentals.dto.MessagesDTO;
import com.openclassrooms.ApiRentals.model.Messages;

import com.openclassrooms.ApiRentals.service.MessageService;

@RestController
@RequestMapping("/api")
public class MessageController {
	
	@Autowired
	private MessageService messageService;

	
	 /**
     * @param newMessageDTO
     * @return un json avec un message de reussite
     */
	@PostMapping("/messages")
    public ResponseEntity<ApiResponse> newMessage(@RequestBody MessagesDTO newMessageDTO) {
        Messages message = convertToEntity(newMessageDTO);
        messageService.savedMessage(message);
        ApiResponse response = new ApiResponse("Message sent with success");
        return ResponseEntity.ok(response);
    }

	/**
	 * methode permettant de convertir un messageDTO en entity de type message
	 * @param messageDTO
	 * @return un message entity
	 */
    private Messages convertToEntity(MessagesDTO messageDTO) {
        Messages message = new Messages();
        message.setRentalid(messageDTO.getRentalId());
        message.setOwner(messageDTO.getOwnerId());
        message.setMessage(messageDTO.getMessage());
        return message;
    }

}
