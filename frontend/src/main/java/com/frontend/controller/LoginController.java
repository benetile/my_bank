package com.frontend.controller;

import com.frontend.Beans.User;
import com.frontend.config.SecurityConfig;
import com.frontend.feign.UserFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    private UserFeign userFeign;

    @Autowired
    private SecurityConfig securityConfig;

    @GetMapping("/register")
    public String registerPage(Model model,User user){
        model.addAttribute("user",user);
        return "home/register";
    }

    @PostMapping("/register")
    public String register(@Valid User user,@RequestParam("confirmPassword") String confirmPassword,
                           BindingResult result){
        if (!result.hasErrors()){
            if (user.getPassword().equals(confirmPassword)){
               //BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
               // user.setPassword(encoder.encode(user.getPassword()));
                userFeign.addUser(user);
                return "redirect:/home/login";
            }
        }
        return "home/register";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "home/login";
    }

    @GetMapping("/app-logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth!=null){
            new SecurityContextLogoutHandler().logout(request,response,auth);
        }
        return "home/login";
    }
}
