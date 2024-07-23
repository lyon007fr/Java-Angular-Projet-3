package com.openclassrooms.ApiRentals.controller;

import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.openclassrooms.ApiRentals.dto.ApiResponse;
import com.openclassrooms.ApiRentals.dto.AuthRequest;
import com.openclassrooms.ApiRentals.dto.JwtResponse;
import com.openclassrooms.ApiRentals.dto.UserDTO;
import com.openclassrooms.ApiRentals.model.User;
import com.openclassrooms.ApiRentals.service.JWTService;
import com.openclassrooms.ApiRentals.service.UserService;

@RestController
@RequestMapping("/api")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private JWTService jwtService;

	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        logger.info("Login attempt for user with email: {}", authRequest.getEmail());
        try {
            // Find user by email
            User user = userService.findByEmail(authRequest.getEmail());
            logger.warn("voici l'utilisateur trouvé {}", user.getPassword());
            if (user == null || !bCryptPasswordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
                logger.warn("Login failed for user with email: {} - Invalid email or password", authRequest.getEmail());
                return ResponseEntity.status(401).body("Invalid email or password");
            }

            // Authenticate user
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));

            // Generate token
            String token = jwtService.generateToken(authentication);
            JwtResponse jwtResponse = new JwtResponse(token);
            logger.info("Login successful for user with email: {}", authRequest.getEmail());

            return ResponseEntity.ok(jwtResponse);
        } catch (AuthenticationException e) {
            logger.error("Login failed for user with email: {}", authRequest.getEmail(), e);
            return ResponseEntity.status(401).body("Invalid email or password");
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/auth/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) {
        logger.info("Registration attempt for user: {}", userDTO.getName());
        try {
            if (userService.findByUsername(userDTO.getName()) != null) {
                logger.warn("Registration failed: User with name {} already exists", userDTO.getName());
                return ResponseEntity.badRequest().body(new ApiResponse("User with the same name already exists"));
            }
            if (userService.findByEmail(userDTO.getEmail()) != null) {
                logger.warn("Registration failed: User with email {} already exists", userDTO.getEmail());
                return ResponseEntity.badRequest().body(new ApiResponse("User with the same email already exists"));
            }

            // CReate new user
            User newUser = new User();
            newUser.setEmail(userDTO.getEmail());
            newUser.setUsername(userDTO.getName());
            newUser.setPassword(userDTO.getPassword());
            newUser.setCreated_at(LocalDateTime.now());
            newUser.setUpdated_at(LocalDateTime.now());
            newUser.setRole("ROLE_USER");

            userService.saveUser(newUser);

            // Authenticate newly created user
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDTO.getName(),userDTO.getPassword());
          

            String token = jwtService.generateToken(authentication);
            JwtResponse jwtResponse = new JwtResponse(token);

            logger.info("Registration successful for user: {}", userDTO.getName());
            return ResponseEntity.ok(jwtResponse);
        } catch (IllegalArgumentException e) {
            logger.error("Registration failed: {}", e.getMessage());
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage()));
        }
    }
}
