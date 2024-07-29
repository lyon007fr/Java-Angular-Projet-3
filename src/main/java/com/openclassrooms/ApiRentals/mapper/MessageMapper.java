package com.openclassrooms.ApiRentals.mapper;

import com.openclassrooms.ApiRentals.dto.MessagesDTO;
import com.openclassrooms.ApiRentals.model.Messages;

public class MessageMapper {

	
	/**
	 * convert a messageDTO to an entity of message type
	 * @param messageDTO
	 * @return a message entity
	 */
    public static Messages convertToEntity(MessagesDTO messageDTO) {
        Messages message = new Messages();
        message.setRentalid(messageDTO.getRentalId());
        message.setOwner(messageDTO.getOwnerId());
        message.setMessage(messageDTO.getMessage());
        return message;
    }
}
