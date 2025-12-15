package com.example.Demo.CustomerRelationshipManagement.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  
    
    private Long itemName;

    private String imageUrl;  

    @Column(length = 500)
    private String itemDescription;  

    private String hsnSac;  

    private Integer quantity;  
    
    private String unit;  

    private Double rate;  

    private Double discount;  

    private Double taxable;  

    private Double cgst;  

    private Double sgst;  

    private Double amount;  

    private String leadTime;  
}