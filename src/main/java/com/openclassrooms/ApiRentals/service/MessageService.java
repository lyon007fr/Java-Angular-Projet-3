package com.openclassrooms.ApiRentals.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.ApiRentals.model.Messages;
import com.openclassrooms.ApiRentals.repository.MessageRepository;
import lombok.Data;


@Data
@Service
public class MessageService {
	
	@Autowired
	private MessageRepository messageRepository;
	
	
	/**
	 * 
	 * @return list of message
	 */
	public Iterable<Messages> getMessages(){
		return messageRepository.findAll();
		
	}
	
	/**
	 * Create a message
	 * @param message
	 * @return The message object saved
	 */
	public Messages savedMessage(Messages message) {
		return messageRepository.save(message);
	}
	

}
