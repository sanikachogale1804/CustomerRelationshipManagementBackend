package com.example.Demo.CustomerRelationshipManagement.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.Demo.CustomerRelationshipManagement.Entity.User;
import com.example.Demo.CustomerRelationshipManagement.Repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository repo;

    // Constructor injection is recommended
    @Autowired
    public MyUserDetailsService(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch user as Optional
        Optional<User> optionalUser = repo.findByUsername(username);

        // Throw exception if user not found
        User user = optionalUser.orElseThrow(
            () -> new UsernameNotFoundException("User not found with username: " + username)
        );

        // Return Spring Security UserDetails
        return new org.springframework.security.core.userdetails.User(
            user.getUsername(),
            user.getPassword(),
            new ArrayList<>() // authorities, adjust as needed
        );
    }
}