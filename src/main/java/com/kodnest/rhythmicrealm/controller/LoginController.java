package com.kodnest.rhythmicrealm.controller;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kodnest.rhythmicrealm.dto.LoginRequest;
import com.kodnest.rhythmicrealm.dto.PasswordResetRequest;
import com.kodnest.rhythmicrealm.entity.User;
import com.kodnest.rhythmicrealm.service.UserService;

@CrossOrigin("*")
@RestController
public class LoginController {

	@Autowired
	UserService userService;

	
//	@PostMapping("/api/login") // Change the mapping to match the endpoint expected by the React component
//	public ResponseEntity<String> loginUser(@RequestBody LoginRequest credentials) {
//		 String usernameOrEmail = credentials.getUsernameOrEmail();
//		    String password = credentials.getPassword();
//	    boolean exists = userService.usernameOrEmailExists(usernameOrEmail);
//
//	    if (exists)  {
//	        boolean authenticated = userService.authenticateUser(usernameOrEmail, password);
//	        if (authenticated) {
//	            User user = userService.findByUsernameOrEmail(usernameOrEmail);
//	            String role = user.getRole();      
//	            if ("admin".equalsIgnoreCase(role)) {
//	                return ResponseEntity.ok().body("{\"message\": \"adminhome\"}");
//	            } else if ("customer".equalsIgnoreCase(role)) {
//	                return ResponseEntity.ok().body("{\"message\": \"customerhome\"}");
//	            } else {
//	                return ResponseEntity.ok().body("{\"message\": \"unknown\"}");
//	            }
//
//	        } else {
//	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect password. Please try again.");
//	        }
//	    } else {
//	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User does not exist. Please register first.");
//	    }
//	}
	
	@PostMapping("/api/login")
	public ResponseEntity<?> loginUser(@RequestBody LoginRequest credentials) {
	    String usernameOrEmail = credentials.getUsernameOrEmail();
	    String password = credentials.getPassword();
	    boolean exists = userService.usernameOrEmailExists(usernameOrEmail);

	    if (exists) {
	        boolean authenticated = userService.authenticateUser(usernameOrEmail, password);
	        if (authenticated) {
	            User user = userService.findByUsernameOrEmail(usernameOrEmail);
	            String role = user.getRole();
	            Long userId = user.getId(); // Assuming this is how you retrieve the user ID

	            JSONObject response = new JSONObject();
	            response.put("message", role);
	            response.put("userId", userId); // Add user ID to the response

	            return ResponseEntity.ok().body(response.toString());
	        } else {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect password. Please try again.");
	        }
	    } else {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User does not exist. Please register first.");
	    }
	}


	@GetMapping("/api/users")
    public ResponseEntity<List<User>> getUsers() {
		System.out.println("requestRecived");
        List<User> userList = userService.getAllUsers();
        return ResponseEntity.ok(userList);
    }
	
	@GetMapping("/api/check-user/{emailOrUsername}")
    public ResponseEntity<?> checkUserExists(@PathVariable String emailOrUsername) {
        // Check if the user with the given email or username exists
        boolean userExists = userService.usernameOrEmailExists(emailOrUsername);
        if (userExists) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with the provided email/username does not exist");
        }
    }
	
	 @PostMapping("/api/reset-password")
	    public ResponseEntity<?> resetPassword(@RequestBody PasswordResetRequest request) {
	        // Perform password reset logic here using the provided email/username and new password
		 	System.out.println(request);
	        try {
	            userService.resetPassword(request.getEmailOrUsername(), request.getNewPassword());
	            return ResponseEntity.ok().build();
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while resetting the password");
	        }
	    }

}
