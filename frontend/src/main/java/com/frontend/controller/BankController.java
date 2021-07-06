package com.frontend.controller;

import com.frontend.Beans.Bank;
import com.frontend.Beans.User;
import com.frontend.feign.BankFeign;
import com.frontend.feign.UserFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
public class BankController {

    @Autowired
    private BankFeign bankFeign;

    @Autowired
    private UserFeign userFeign;

    @GetMapping("/admin/banks")
    public String showAllBanks(Principal principal, Model model){
        User user = userFeign.getUserWithEmail(principal.getName());
        List<Bank> banks = bankFeign.showAllBanks();
        model.addAttribute("user",user);
        model.addAttribute("userBank",user.getBank());
        model.addAttribute("banks",banks);
        return "bank/attributeBank";
    }

    @GetMapping("/admin/bank")
    public String beforeCreateBank(Principal principal,Model model){
        User user = userFeign.getUserWithEmail(principal.getName());
        Bank bank = new Bank();
        model.addAttribute("user",user);
        model.addAttribute("bank",bank);
        return "bank/createBank";
    }

    @PostMapping("/admin/bank")
    public String addBank(@Valid Bank bank, BindingResult result){
        if (!result.hasErrors()){
            bankFeign.createBank(bank);
            return "redirect:/admin/banks";
        }
        else
            return "bank/createBank";
    }

    @GetMapping("/admin/attributeBank/{id}")
    public String attributeBank(@PathVariable("id") Integer id, Principal principal, Model model){
        Bank bank = bankFeign.getBank(id);
        User user = userFeign.getUserWithEmail(principal.getName());
        if (user.getBank()==null){
            user.setBank(bank);
            userFeign.updateUser(user.getIdUser(),user);
            model.addAttribute("user",user);
            model.addAttribute("bank",user.getBank());
            return "redirect:/admin";
        }
        else
            return "bank/attributeBank";
    }

    @GetMapping("/admin/deleteBank/{id}")
    public String deleteBank(@PathVariable("id") Integer id, Principal principal,Model model){
        User user = userFeign.getUserWithEmail(principal.getName());
        Bank bank = bankFeign.getBank(id);
        if (user.getBank().getBranchCode().equals(bank.getBranchCode())){ ;
        }
        return null;
    }

}
