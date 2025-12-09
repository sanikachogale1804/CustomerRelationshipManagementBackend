package com.example.Demo.CustomerRelationshipManagement.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.Demo.CustomerRelationshipManagement.Entity.Interaction;

@CrossOrigin(origins = {"http://localhost:3000"})
public interface InteractionRepository extends JpaRepository<Interaction, Long> {

}
