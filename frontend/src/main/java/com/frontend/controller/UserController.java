package com.frontend.controller;

import com.frontend.Beans.User;
import com.frontend.feign.UserFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class UserController {

    @Autowired
    private UserFeign userFeign;

    @GetMapping("/user")
    public String homePage(Principal principal, Model model){
        User user = userFeign.getUserWithEmail(principal.getName());
        model.addAttribute("user",user);
        return "user/index";
    }
}
