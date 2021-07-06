package com.frontend.controller;

import com.frontend.Beans.Account;
import com.frontend.Beans.Bank;
import com.frontend.Beans.User;
import com.frontend.config.GeneratePassword;
import com.frontend.feign.AccountFeign;
import com.frontend.feign.UserFeign;
import com.frontend.service.GenerateAccountNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class AdminController {

    @Autowired
    private UserFeign userFeign;

    @Autowired
    private AccountFeign accountFeign;

    @Autowired
    private GeneratePassword generatePassword;

    @Autowired
    private GenerateAccountNumber generateAccountNumber;

    @GetMapping("/admin")
    public String adminHome(Principal principal, Model model){
        User user = userFeign.getUserWithEmail(principal.getName());
        model.addAttribute("user",user);
        model.addAttribute("bank",user.getBank());
        return "admin/index";
    }

    @GetMapping("/admin/add-user")
    public String infoUser(Model model){
        model.addAttribute("user",new User());
        return "admin/createUser";
    }

    @PostMapping("/admin/add-user")
    public String createUser(@Valid User user,Principal principal, BindingResult result,Model model){
        User admin = userFeign.getUserWithEmail(principal.getName());
        Bank bank = admin.getBank();
        Account account = new Account();
        if (!result.hasErrors()){
            user.setRole("USER");
            user.setPassword(generatePassword.generateStrongPassword());
            System.out.println(user.getPassword());
            if (user!=null){
                user.setBank(bank);
                userFeign.addUser(user);
                model.addAttribute("userSave",user);
                model.addAttribute("message","The operation was successful");
                return "admin/message";
            }
        }
        return "redirect:/admin/add-user";
    }

    @PostMapping("/admin/createAccount")
    public String createAccount(@Valid Account account, BindingResult result){
        if (!result.hasErrors()){

        }
        return null;
    }


    @PostMapping("/admin/search")
    public String searchUser(@RequestParam("name") String name,Model model){
        return null;
    }

}
