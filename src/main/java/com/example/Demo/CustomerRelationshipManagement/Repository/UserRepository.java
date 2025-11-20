package com.example.Demo.CustomerRelationshipManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.Demo.CustomerRelationshipManagement.Entity.User;

@CrossOrigin
public interface UserRepository extends JpaRepository<User, Long>{
	
	

}
