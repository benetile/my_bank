package com.user.service;

import com.user.model.User;
import com.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthenticateUser {

    @Autowired
    private UserRepository userRepository;

    public boolean hasId(Integer id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Id "));
        return user.getEmail().equals(userPrincipal.getUsername());
    }
}
