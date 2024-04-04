package com.kodnest.rhythmicrealm.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodnest.rhythmicrealm.entity.User;
import com.kodnest.rhythmicrealm.repository.UserRepository;
import com.kodnest.rhythmicrealm.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public void saveUser(User user) {
		userRepository.save(user);

	}

	@Override
	public boolean usernameOrEmailExists(String usernameOrEmail) {
		return userRepository.existsByUsernameOrEmail(usernameOrEmail, usernameOrEmail);
	}

	@Override
	public boolean authenticateUser(String usernameOrEmail, String password) {

		User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);

		if (user != null && user.getPassword().equals(password)) {
			return true;
		}
		return false;
	}

	@Override
	public User findByUsernameOrEmail(String usernameOrEmail) {
		return userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public void resetPassword(String emailOrUsername, String newPassword) {
		 // Retrieve the user by email or username
        User user = userRepository.findByUsernameOrEmail(emailOrUsername, emailOrUsername);
        // Update the user's password
        user.setPassword(newPassword);

        // Save the updated user entity
        userRepository.save(user);
		
	}

	@Override
	public boolean existsByUsername(String username) {
		return userRepository.existsByUsername(username);
	}

	@Override
	public boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}

	@Override
	public void updatePremiumStatus(Long userId,boolean isPremium) {
		   // Assuming your UserRepository has a method to find a user by ID
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        
        // Update the premium status
        user.setIspremium(isPremium);
        
        // Save the updated user
        userRepository.save(user);
	}

	@Override
	 public boolean isUserPremium(Long userId) {
        // Call UserRepository method to fetch user by userId
        User user = userRepository.findById(userId).orElse(null);

        // Check if user exists and if user is premium
        return user != null && user.isIspremium();
    }

	

}
