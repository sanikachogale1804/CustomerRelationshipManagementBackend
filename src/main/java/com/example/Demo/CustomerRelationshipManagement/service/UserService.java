package com.example.Demo.CustomerRelationshipManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Demo.CustomerRelationshipManagement.Entity.User;
import com.example.Demo.CustomerRelationshipManagement.Repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private JWTService jwtService;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
	
	public User register(User user) {
		user.setPassword(encoder.encode(user.getPassword())); 
		return repo.save(user);
	}
	
	public String verify(User user) {
		Authentication authentication =
				authManager.authenticate(
						new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
				);

		if (authentication.isAuthenticated()) 
			return jwtService.generateToken(user.getUsername());
		return "fail";
	}
	
	public User updateUser(Long id, User user) {
	    User existing = repo.findById(id)
	            .orElseThrow(() -> new RuntimeException("User not found: " + id));
	    
	    existing.setFullName(user.getFullName());
	    existing.setUsername(user.getUsername());
	    existing.setMobileNo(user.getMobileNo());
	    existing.setDesignation(user.getDesignation());
	    // Add other fields if needed
	    
	    return repo.save(existing);
	}
	
	// Change password for logged-in user
	public boolean changePassword(String username, String oldPassword, String newPassword) {
	    User user = repo.findByUsername(username)
	            .orElseThrow(() -> new RuntimeException("User not found"));

	    // Check old password
	    if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
	        return false; // old password incorrect
	    }

	    // Encode and update new password
	    user.setPassword(passwordEncoder.encode(newPassword));
	    repo.save(user);
	    return true;
	}

	
	public void adminResetPassword(Long userId, String newPassword) {
	    User user = repo.findById(userId)
	            .orElseThrow(() -> new RuntimeException("User not found"));

	    // Encode and update password
	    user.setPassword(passwordEncoder.encode(newPassword));
	    repo.save(user);
	}
	
	public User getUserById(Long id) {
		return repo.findById(id)
				.orElseThrow(() -> new RuntimeException("User Not found with ID: "+ id));
	}

	
}
