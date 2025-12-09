package com.example.Demo.CustomerRelationshipManagement.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Quotation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Basic Information
    private String customer; // Customer name or ID
    private String branch;   // Branch name or ID
    private String copyFrom; // Optional: copy existing quotation

    // Party Details
    private String contactPerson;
    private String address;
    private String shippingAddress;
    private String salesCredit;

    // Document Details
    private String quotationNumber;
    private String reference;
    @Temporal(TemporalType.DATE)
    private Date quotationDate;
    @Temporal(TemporalType.DATE)
    private Date validTill;

    // Terms & Conditions
    @ElementCollection
    private List<String> terms;

    // Notes & Bank Details
    @Column(length = 2000)
    private String notes;
    @Column(length = 1000)
    private String bankDetails;

    // Extras / Discounts
    @ElementCollection
    private List<ExtraCharge> extraCharges;
    @ElementCollection
    private List<Discount> discounts;

    // Totals
    private Double total;
    private Double grandTotal;

    private String uploadedFile;

}

@Embeddable
class ExtraCharge {
    private String description;
    private Double amount;
}

@Embeddable
class Discount {
    private String description;
    private Double amount;
}
