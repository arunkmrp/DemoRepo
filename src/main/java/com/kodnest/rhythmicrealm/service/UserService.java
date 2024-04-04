package com.kodnest.rhythmicrealm.service;

import java.util.List;

import com.kodnest.rhythmicrealm.entity.User;

public interface UserService {
	User findByUsername(String username);

	User findByEmail(String email);

	void saveUser(User user);

	boolean usernameOrEmailExists(String usernameOrEmail);

	boolean authenticateUser(String usernameOrEmail, String password);

	User findByUsernameOrEmail(String usernameOrEmail);

	List<User> getAllUsers();

	void resetPassword(String emailOrUsername, String newPassword);

	boolean existsByUsername(String username);

	boolean existsByEmail(String email);

	void updatePremiumStatus(Long userId, boolean isPremium);

	boolean isUserPremium(Long userId);

	

}
