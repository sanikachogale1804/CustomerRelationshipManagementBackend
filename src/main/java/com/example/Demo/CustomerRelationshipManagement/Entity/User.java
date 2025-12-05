package com.example.Demo.CustomerRelationshipManagement.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
	
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String fullName;
	
	private String username;
	
	private String password;
	
	private String designation;
	
	private String mobileNo;
	
	private String email;
	
	private LocalDate dateOfBirth;
	
	private LocalDate dateOfJoining;

    private String workLocation;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;
	
	@Lob
	private String aadharCardPath;
	
	@Lob
	private String panCardPath;
	
	@Lob
	private String photoPath;
	
	@Lob
	private String certificatePath;
	
	@ManyToOne
	@JoinColumn(name = "reporting_to_id")
	private User reportingTo;
	
	@OneToMany(mappedBy = "assignedTo")
	private List<Lead> leads;

	
	public enum Department{
		Operation_Project, 
		Finance_Account,
		Sales_Marketing,
		InformationTechnology,
		HumanResource,
		Purchase_logistic
	}
	
	public enum Role{
		SuperAdmin,
		Admin,
		HeadOfDepartment, 
		Manager,
		Supervisor,
		User
	}
	
	public enum Payroll{
		On_Payroll,
		Contract
	}
	
	public enum EmloymentStatus{
		Active,
		Inactive,
		Resigned,
		Terminated
	}
	

}
