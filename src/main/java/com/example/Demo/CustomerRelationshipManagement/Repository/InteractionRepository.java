package com.example.Demo.CustomerRelationshipManagement.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Demo.CustomerRelationshipManagement.Entity.Interaction;

public interface InteractionRepository extends JpaRepository<Interaction, Long> {

}
