package com.kodnest.rhythmicrealm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kodnest.rhythmicrealm.dto.RegisterRequest;
import com.kodnest.rhythmicrealm.entity.User;
import com.kodnest.rhythmicrealm.service.UserService;

@CrossOrigin("*")
@RestController
public class RegistrationController {

	@Autowired
	UserService userService;
	
	 @PostMapping("/api/register")
	    public ResponseEntity<String> registerUser(@RequestBody RegisterRequest userDTO) {
	        try {
	            // Check if username or email already exists
	            if (userService.existsByUsername(userDTO.getUsername())) {
	                return ResponseEntity.badRequest().body("Username already exists. Please choose another.");
	            }
	            if (userService.existsByEmail(userDTO.getEmail())) {
	                return ResponseEntity.badRequest().body("Email already exists. Please choose another.");
	            }

	            // Create a new user entity
	            User newUser = new User();
	            newUser.setUsername(userDTO.getUsername());
	            newUser.setEmail(userDTO.getEmail());
	            newUser.setPassword(userDTO.getPassword());
	            newUser.setGender(userDTO.getGender());
	            newUser.setRole(userDTO.getRole());

	            // Save the new user
	            userService.saveUser(newUser);
	            
	            return ResponseEntity.ok("User registered successfully!");
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                                 .body("Failed to register user. Please try again later.");
	        }
	    }

}
