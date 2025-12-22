package com.example.Demo.CustomerRelationshipManagement.Controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.Demo.CustomerRelationshipManagement.Entity.User;
import com.example.Demo.CustomerRelationshipManagement.Repository.UserRepository;
import com.example.Demo.CustomerRelationshipManagement.service.FileService;
import com.example.Demo.CustomerRelationshipManagement.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService service;
	
	@Autowired
    private FileService fileService; 
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/register")
	public ResponseEntity<User> registerUser(
	        @RequestParam("employeeId") String employeeId,
	        @RequestParam("fullName") String fullName,
	        @RequestParam("email") String email,
	        @RequestParam("username") String username,
	        @RequestParam("password") String password,
	        @RequestParam("dob") String dob,
	        @RequestParam("doj") String doj,
	        @RequestParam("designation") String designation,
	        @RequestParam("department") String department,
	        @RequestParam("payroll") String payroll,
	        @RequestParam("employmentType") String employmentType,
	        @RequestParam("workLocation") String workLocation,
	        @RequestParam(value = "mobileNo", required = false) String mobileNo,
	        @RequestParam(value = "reportingToId", required = false) Long reportingToId,
	        @RequestPart(value = "photo", required = false) MultipartFile photo,
	        @RequestPart(value = "aadharCard", required = false) MultipartFile aadharCard,
	        @RequestPart(value = "panCard", required = false) MultipartFile panCard,
	        @RequestPart(value = "certificate",required = false) MultipartFile certificate 
	) throws IOException {

	    User user = new User();
	    user.setFullName(fullName);
	    user.setUsername(username);
	    user.setPassword(password);
	    user.setDesignation(designation);
	    user.setMobileNo(mobileNo != null ? mobileNo : "");
	    user.setEmail(email);
	    user.setDateOfBirth(LocalDate.parse(dob));
	    user.setDateOfJoining(LocalDate.parse(doj));
	    user.setWorkLocation(workLocation);
	    user.setCreatedAt(LocalDateTime.now());
	    user.setUpdatedAt(LocalDateTime.now());

	    // Handle reportingTo relationship
	    if (reportingToId != null) {
	        User reportingTo = service.getUserById(reportingToId);
	        user.setReportingTo(reportingTo);
	    }

	    // File uploads
	    if (aadharCard != null) {
	        String path = "uploads/aadhar";
	        String fileName = fileService.uploadFile(aadharCard, path);
	        user.setAadharCardPath(path + "/" + fileName);
	    }

	    if (panCard != null) {
	        String path = "uploads/pan";
	        String fileName = fileService.uploadFile(panCard, path);
	        user.setPanCardPath(path + "/" + fileName);
	    }

	    if (photo != null) {
	        String path = "uploads/photo";
	        String fileName = fileService.uploadFile(photo, path);
	        user.setPhotoPath(path + "/" + fileName);
	    }
	    
	    if (certificate != null) {
	        String path = "uploads/certificate";
	        String fileName = fileService.uploadFile(certificate, path);
	        user.setCertificatePath(path + "/" + fileName);
	    }


	    User saved = service.register(user);
	    return ResponseEntity.ok(saved);
	}


	@GetMapping("/users/{id}")
	public EntityModel<User> getUserById(@PathVariable Long id) {
	    User user = userRepository.findById(id)
	        .orElseThrow(() -> new RuntimeException("User not found"));
	    return EntityModel.of(user,
	        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class)
	            .getUserById(id)).withSelfRel());
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

	
	@PutMapping("/users/change-password")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<String> changePassword(
	        @RequestBody Map<String, String> body,
	        @AuthenticationPrincipal UserDetails userDetails) {

	    String oldPassword = body.get("oldPassword");
	    String newPassword = body.get("newPassword");

	    boolean updated = service.changePassword(userDetails.getUsername(), oldPassword, newPassword);

	    if (updated) {
	        return ResponseEntity.ok("Password changed successfully!");
	    } else {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Old password is incorrect.");
	    }
	}
	
	@PutMapping("/admin/users/{id}/reset-password")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<String> resetUserPassword(
	        @PathVariable Long id,
	        @RequestBody Map<String, String> body) {

	    String newPassword = body.get("newPassword");
	    try {
	        service.adminResetPassword(id, newPassword);
	        return ResponseEntity.ok("Password reset successfully for user ID: " + id);
	    } catch (RuntimeException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                             .body("User not found");
	    }
	}





}
