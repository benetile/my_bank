package com.frontend.controller;

import com.frontend.Beans.User;
import com.frontend.feign.UserFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class AdminController {

    @Autowired
    private UserFeign userFeign;

    @GetMapping("/admin")
    public String adminHome(Principal principal, Model model){
        User user = userFeign.getUserWithEmail(principal.getName());
        model.addAttribute("user",user);
        return "admin/index";
    }

    @PostMapping("/admin/search")
    public String searchUser(@RequestParam("name") String name,Model model){
        return null;
    }

}
