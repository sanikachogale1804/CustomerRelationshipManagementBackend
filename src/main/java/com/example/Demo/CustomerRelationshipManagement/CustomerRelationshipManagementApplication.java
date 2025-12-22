package com.example.Demo.CustomerRelationshipManagement;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//import com.example.Demo.CustomerRelationshipManagement.service.EmailService;
//
//import jakarta.mail.MessagingException;

@SpringBootApplication
public class CustomerRelationshipManagementApplication implements CommandLineRunner {

//    private final EmailService emailService;

//    public CustomerRelationshipManagementApplication(EmailService emailService) {
//        this.emailService = emailService;
//    }

    public static void main(String[] args) {
        SpringApplication.run(CustomerRelationshipManagementApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Test email
//        try {
//            emailService.sendLeadNotification(
//                "sanika@cogentsecurity.ai",  
//                "John Doe",
//                "Demo Business"
//            );
//            System.out.println("Test email sent successfully!");
//        } catch (MessagingException e) {
//            System.err.println("Failed to send test email: " + e.getMessage());
//        }
    }
}
