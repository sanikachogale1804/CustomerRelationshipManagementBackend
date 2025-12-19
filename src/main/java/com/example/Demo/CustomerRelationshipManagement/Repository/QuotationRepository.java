package com.example.Demo.CustomerRelationshipManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.Demo.CustomerRelationshipManagement.Entity.Quotation;

@CrossOrigin
public interface QuotationRepository extends JpaRepository<Quotation, Long>{

}
