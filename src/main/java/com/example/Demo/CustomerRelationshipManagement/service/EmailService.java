package com.example.Demo.CustomerRelationshipManagement.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendLeadNotification(String toEmail, String leadName, String business) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(fromEmail);
        helper.setTo(toEmail);
        helper.setSubject("New Lead Assigned: " + leadName);
        helper.setText("Hello " + leadName + ",\n\n"
                + "A new lead for business \"" + business + "\" has been assigned to you.\n\n"
                + "Thank you,\nCRM Team");

        mailSender.send(message);
    }
    
    
}