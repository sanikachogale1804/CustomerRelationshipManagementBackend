package com.example.Demo.CustomerRelationshipManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.Demo.CustomerRelationshipManagement.Entity.User;

@CrossOrigin(origins = {"http://localhost:3000"})
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByUsername(String username);

}
