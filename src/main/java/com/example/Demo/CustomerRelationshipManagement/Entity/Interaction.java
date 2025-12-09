package com.example.Demo.CustomerRelationshipManagement.Entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Interaction {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lead_id")
    private Lead lead;  // Lead entity ka reference

    private LocalDate date;
    private LocalTime time;
    
    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "tag_location")
    private String tagLocation;

    private String type;

    @Column(columnDefinition = "TEXT")
    private String note;

    // Next Appointment fields (optional)
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_to")
    private User assignedTo;  // User entity reference

    @Column(name = "appointment_type")
    private String appointmentType;

   
}
