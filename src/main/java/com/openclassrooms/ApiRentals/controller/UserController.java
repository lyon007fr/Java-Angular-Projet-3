package com.openclassrooms.ApiRentals.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.ApiRentals.dto.UserDTO;
import com.openclassrooms.ApiRentals.mapper.UserMapper;
import com.openclassrooms.ApiRentals.model.User;
import com.openclassrooms.ApiRentals.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/auth/me")
    public ResponseEntity<UserDTO> getCurrentUser() {
    	
    	//get info about authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        
        //find user based from the authenticated information
        User user = userService.findByUsername(username);
        if (user == null) {
        	
        		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        	}
        
        
        	UserDTO userDTO = UserMapper.convertToDTO(user);
            return ResponseEntity.status(HttpStatus.OK).body(userDTO);
        	
            
        }

        
       
    //Get user by id
    @GetMapping("/user/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
    	Long.valueOf(id);    	
        Optional<User> userOptional = userService.findById(id);

        if (!userOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        UserDTO userDTO = UserMapper.convertToDTO(userOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body(userDTO);
    }
    
}