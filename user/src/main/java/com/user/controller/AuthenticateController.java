package com.user.controller;

import com.user.beans.AccountBean;
import com.user.beans.BankBean;
import com.user.config.JwtUtil;
import com.user.model.JwtRequest;
import com.user.model.User;
import com.user.repository.UserRepository;
import com.user.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

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


    @PostMapping("/authenticate")
    public String createAuthenticationToken(@RequestBody JwtRequest authRequest) throws Exception{
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        }catch (BadCredentialsException e){
            throw new Exception("Invalid Email or Password " +authRequest.getUsername()+ ": " +authRequest.getPassword());
        }

        final UserDetails userDetails = myUserDetailsService.loadUserByUsername(authRequest.getUsername());

        //final String token = jwtUtil.generateToken(userDetails);
       // JwtResponse jwtResponse = new JwtResponse(jwtUtil.generateToken(authRequest.getUsername()));
        System.out.println(jwtUtil.generateToken(authRequest.getUsername()));
        System.out.println(authRequest.getUsername()+ " : "+ authRequest.getPassword());

        return jwtUtil.generateToken(authRequest.getUsername());
    }

    @GetMapping("/currentUser")
    public User getCurrentUser(Principal principal){
        return userRepository.findByEmail(principal.getName());
    }

    @GetMapping("/UserBank")
    public BankBean welcome(Principal principal) {
        User user = userRepository.findByEmail(principal.getName());
        return user.getBank();
    }

    @GetMapping("/accountUser")
    public AccountBean getCurrentUserAccount(Principal principal){
        User user = userRepository.findByEmail(principal.getName());
        return user.getAccount();
    }


}
