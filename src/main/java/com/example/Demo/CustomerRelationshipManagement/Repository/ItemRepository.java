package com.example.Demo.CustomerRelationshipManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.Demo.CustomerRelationshipManagement.Entity.Item;

@CrossOrigin
public interface ItemRepository extends JpaRepository<Item, Long>{

}
