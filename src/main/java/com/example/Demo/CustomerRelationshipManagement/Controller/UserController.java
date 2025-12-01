package com.example.Demo.CustomerRelationshipManagement.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Demo.CustomerRelationshipManagement.Entity.User;
import com.example.Demo.CustomerRelationshipManagement.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService service;
	
	@CrossOrigin(origins =  "http://localhost:3000")
	@PostMapping("/register")
	public User register(@RequestBody User user) {
		return service.register(user);
		
	}
	
	@CrossOrigin(origins =  "http://localhost:3000")
	@PostMapping("/login")
	public String login(@RequestBody User user) {
		return service.verify(user);
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        try {
            User updated = service.updateUser(id, user);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).build(); // user not found
        } catch (Exception e) {
            return ResponseEntity.status(500).build(); // server error
        }
    }

}
