package com.openclassrooms.ApiRentals.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.ApiRentals.dto.JwtResponse;
import com.openclassrooms.ApiRentals.service.JWTService;




@RestController
@RequestMapping("/api")
public class LoginController {


	private JWTService jwtService;
	
	public LoginController(JWTService jwtService) {
		this.jwtService = jwtService;
	}
	
	  @PostMapping("/login")
	    public ResponseEntity<JwtResponse> getToken(Authentication authentication) {
	        String token = jwtService.generateToken(authentication);
	        JwtResponse jwtResponse = new JwtResponse(token);
	        return ResponseEntity.ok(jwtResponse);
	    }
	  
	  @PostMapping("/auth/register")
	    public ResponseEntity<JwtResponse> authenticateUser(Authentication authentication) {
	        return getToken(authentication);
	    }
	
}

