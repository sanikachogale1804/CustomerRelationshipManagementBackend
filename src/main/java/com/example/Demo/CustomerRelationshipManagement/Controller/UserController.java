package com.example.Demo.CustomerRelationshipManagement.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Demo.CustomerRelationshipManagement.Entity.User;
import com.example.Demo.CustomerRelationshipManagement.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService service;
	
	@PostMapping("/register")
	public User register(@RequestBody User user) {
		return service.register(user);
		
	}
	
	@PostMapping("/login")
	public String login(@RequestBody User user) {
		return service.verify(user);
	}

}
