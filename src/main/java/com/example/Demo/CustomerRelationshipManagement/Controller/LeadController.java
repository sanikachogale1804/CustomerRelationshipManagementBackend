package com.example.Demo.CustomerRelationshipManagement.Controller;

import com.example.Demo.CustomerRelationshipManagement.Entity.Lead;
import com.example.Demo.CustomerRelationshipManagement.Entity.User;
import com.example.Demo.CustomerRelationshipManagement.Repository.LeadRepository;
import com.example.Demo.CustomerRelationshipManagement.Repository.UserRepository;
import com.example.Demo.CustomerRelationshipManagement.service.EmailService;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/leads")
public class LeadController {

    @Autowired
    private LeadRepository leadRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    // ✅ Get single lead
    @GetMapping("/{id}")
    public EntityModel<Lead> getLeadById(@PathVariable Long id) {
        Lead lead = leadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lead not found"));

        EntityModel<Lead> leadModel = EntityModel.of(lead,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(LeadController.class)
                        .getLeadById(id)).withSelfRel());

        // HATEOAS link for assignedTo as sub-endpoint
        leadModel.add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(LeadController.class)
                        .getAssignedUser(id)
        ).withRel("assignedTo"));

        return leadModel;
    }

    // ✅ Get all leads with assignedTo HATEOAS links
    @GetMapping
    public CollectionModel<EntityModel<Lead>> getAllLeads() {
        List<EntityModel<Lead>> leads = leadRepository.findAll().stream().map(lead -> {
            EntityModel<Lead> leadModel = EntityModel.of(lead,
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(LeadController.class)
                            .getLeadById(lead.getId())).withSelfRel());

            // assignedTo link
            leadModel.add(WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(LeadController.class)
                            .getAssignedUser(lead.getId())
            ).withRel("assignedTo"));

            return leadModel;
        }).collect(Collectors.toList());

        return CollectionModel.of(leads,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(LeadController.class)
                        .getAllLeads()).withSelfRel());
    }

    // ✅ Create lead + send email
    @PostMapping
    public ResponseEntity<Lead> createLead(@RequestBody Lead lead) {
        lead.setId(null); // ensure new lead
        User assignedUser = null;

        if (lead.getAssignedTo() != null && lead.getAssignedTo().getId() != null) {
            assignedUser = userRepository.findById(lead.getAssignedTo().getId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            lead.setAssignedTo(assignedUser);
        }

        Lead savedLead = leadRepository.save(lead);

        // Send email notification
        if (assignedUser != null && assignedUser.getEmail() != null) {
            try {
                emailService.sendLeadNotification(
                        assignedUser.getEmail(),
                        assignedUser.getFullName(),
                        savedLead.getBusiness()
                );
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(savedLead);
    }

    // ✅ New endpoint: Get assigned user for a lead
    @GetMapping("/{id}/assignedTo")
    public ResponseEntity<User> getAssignedUser(@PathVariable Long id) {
        Lead lead = leadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lead not found"));

        User assignedUser = lead.getAssignedTo();
        if (assignedUser == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(assignedUser);
    }
}
