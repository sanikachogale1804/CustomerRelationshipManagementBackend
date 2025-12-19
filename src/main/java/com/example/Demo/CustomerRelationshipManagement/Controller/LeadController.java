package com.example.Demo.CustomerRelationshipManagement.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.Demo.CustomerRelationshipManagement.Entity.Lead;
import com.example.Demo.CustomerRelationshipManagement.Entity.User;
import com.example.Demo.CustomerRelationshipManagement.Repository.LeadRepository;
import com.example.Demo.CustomerRelationshipManagement.Repository.UserRepository;
import com.example.Demo.CustomerRelationshipManagement.service.EmailService;

import jakarta.mail.MessagingException;

public class LeadController {
	
	private LeadRepository leadRepository;
	
	private EmailService emailService;
	
	private UserRepository userRepository;
	
	@PostMapping("/leads")
	public ResponseEntity<Lead> createLead(@RequestBody Lead lead) {

	    // 1. New lead = ensure id null
	    lead.setId(null);

	    // 2. Assigned user ko fetch kar ke set karo
	    if (lead.getAssignedTo() != null && lead.getAssignedTo().getId() != null) {
	        User user = userRepository.findById(lead.getAssignedTo().getId())
	                  .orElseThrow(() -> new RuntimeException("User not found"));
	        lead.setAssignedTo(user);
	    }

	    Lead savedLead = leadRepository.save(lead);
	    return ResponseEntity.status(HttpStatus.CREATED).body(savedLead);
	}


}
