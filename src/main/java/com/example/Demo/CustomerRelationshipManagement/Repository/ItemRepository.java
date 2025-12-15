package com.example.Demo.CustomerRelationshipManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Demo.CustomerRelationshipManagement.Entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long>{

}
