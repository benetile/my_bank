package com.user.controller;

import com.user.config.JwtUtil;
import com.user.model.JwtRequest;
import com.user.model.JwtResponse;
import com.user.model.User;
import com.user.repository.UserRepository;
import com.user.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class AuthenticateController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;



    @PostMapping("/auth")
    public String createAuthenticationToken(@RequestBody JwtRequest authRequest) throws Exception{
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        }catch (Exception e){
            throw new Exception("Invalid Email or Password");
        }
        return jwtUtil.generateToken(authRequest.getUsername());
    }

    @GetMapping("/")
    public String welcome() {
        return "Bonjour : ";
    }

    @PostMapping("/login")
    public User loginUser(@RequestBody User user) {
        String email = user.getEmail();
        String password = user.getPassword();
        User userLogin = new User();
        if (email != null && password!= null){
            userLogin = userRepository.findByEmailAndPassword(email,password);
        }
        else if(userLogin == null){
            throw new IllegalArgumentException("Bad credential");
        }
        return userLogin;
    }
}
