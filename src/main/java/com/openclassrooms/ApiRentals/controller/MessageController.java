package com.openclassrooms.ApiRentals.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.ApiRentals.dto.ApiResponse;
import com.openclassrooms.ApiRentals.dto.MessagesDTO;
import com.openclassrooms.ApiRentals.mapper.MessageMapper;
import com.openclassrooms.ApiRentals.model.Messages;

import com.openclassrooms.ApiRentals.service.MessageService;

@RestController
@RequestMapping("/api")
public class MessageController {
	
	@Autowired
	private MessageService messageService;

	
	 /**
     * @param newMessageDTO
     * @return json with successfull message
     */
	@PostMapping("/messages")
    public ResponseEntity<ApiResponse> newMessage(@RequestBody MessagesDTO newMessageDTO) {
        Messages message = MessageMapper.convertToEntity(newMessageDTO);
        messageService.savedMessage(message);
        ApiResponse response = new ApiResponse("Message sent with success");
        return ResponseEntity.ok(response);
    }


}
