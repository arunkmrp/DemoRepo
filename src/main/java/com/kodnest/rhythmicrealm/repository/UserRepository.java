package com.kodnest.rhythmicrealm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodnest.rhythmicrealm.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);

	User findByEmail(String email);

	// Method to check if username or email exists
	// SELECT u FROM User u WHERE u.username = :username OR u.email = :email
	boolean existsByUsernameOrEmail(String username, String email);

	// Method to find user by username or email
	User findByUsernameOrEmail(String username, String email);

	boolean existsByUsername(String username);

	boolean existsByEmail(String email);

}
