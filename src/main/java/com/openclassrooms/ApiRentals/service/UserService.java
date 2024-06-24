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
        // Encoder le mot de passe
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        // Enregistrer l'utilisateur dans la BDD
        user.setRole("USER");
        usersRepository.save(user);
    }
    
    public UserDTO convertToDTO(User user) {
        return new UserDTO(
            user.getId(),
            user.getUsername(),
            user.getEmail(),
            user.getCreated(),
            user.getUpdated()
        );
    }
    
    public User findByUsername(String name) {
        return usersRepository.findUserByName(name);
    }
    
    public Optional<User> findById(Long id) {
        return usersRepository.findById(id);
    }
    

}
