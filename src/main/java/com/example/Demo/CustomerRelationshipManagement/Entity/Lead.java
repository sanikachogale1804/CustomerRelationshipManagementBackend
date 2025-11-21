package com.example.Demo.CustomerRelationshipManagement.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "leads")
public class Lead {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
//	core data
	private String Business;
	
	private String address;
	
	private String firstName;
	
	private String lastName;
	
	private String designation;
	
	private String country;
	
	private String mobile;
	
	private String city;
	
	private String state;
	
	private String email;
	
	private String gstin;
	
	private String pincode;
	
	private String website;
	
	private String code;
	
//	business opportunity
	private String source;
	
	private String since;
	
	private String requirement;
	
	private String category;
	
	private String notes;
	
	private String product;
	
	private String potential;
	
	private String assignedTo;
	
	private String stage;
	
	private String tags;
	

}
