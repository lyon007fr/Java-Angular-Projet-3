package com.openclassrooms.ApiRentals.service;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.openclassrooms.ApiRentals.dto.UserDTO;
import com.openclassrooms.ApiRentals.model.User;
import com.openclassrooms.ApiRentals.repository.UsersRepository;

import lombok.Data;

@Data
@Service
public class UserService {
	
	@Autowired
	private UsersRepository usersRepository;
	
	
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void saveUser(User user) {
    	// check if user already exist
    	if (usersRepository.findUserByEmail(user.getEmail()) != null) {
             throw new IllegalArgumentException("Email already in use");
         }
        // encode password
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        // save user in database
        user.setRole("USER");
        usersRepository.save(user);
    }    
   
    
    public UserDTO convertToDTO(User user) {
        return new UserDTO(
            user.getId(),
            user.getUsername(),
            user.getEmail(),
            user.getPassword(),
            user.getCreated_at(),
            user.getUpdated_at()
        );
    }
    
    public User findByUsername(String name) {
    	
        return usersRepository.findUserByName(name);
    }
    
    public Optional<User> findById(Long id) {
        return usersRepository.findById(id);
    }



	public User findByEmail(String email) {
		return usersRepository.findUserByEmail(email);
	}
    

}
