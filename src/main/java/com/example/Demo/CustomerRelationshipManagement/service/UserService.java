package com.example.Demo.CustomerRelationshipManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
}
