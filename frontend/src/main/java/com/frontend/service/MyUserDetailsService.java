package com.frontend.service;

import com.frontend.Beans.User;
import com.frontend.feign.UserFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserFeign userFeign;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        List<SimpleGrantedAuthority>authorities = new ArrayList<>();

        /** search user with email with interface Feign **/
        User user = userFeign.getUserWithEmail(email);

        if (user == null){
            throw new UsernameNotFoundException("Not found ");
        }
        else if ( user.getRole().equals("USER")){
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
        else if(user.getRole().equals("ADMIN")){
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),authorities);
    }
}
