package com.frontend.controller;

import com.frontend.Beans.Account;
import com.frontend.Beans.User;
import com.frontend.feign.AccountFeign;
import com.frontend.feign.UserFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.security.Principal;

@Controller
public class UserController {

    @Autowired
    private UserFeign userFeign;

    @Autowired
    private AccountFeign accountFeign;

    @GetMapping("/user")
    public String homePage(Principal principal, Model model){
        User user = userFeign.getUserWithEmail(principal.getName());
        Account account = user.getAccount();
        BigDecimal percent = ((new BigDecimal(1800).divide(account.getCeiling())).multiply(new BigDecimal(100)));
        BigDecimal discovered = new BigDecimal("0");
        if (account.getDiscoveredMax().equals(account.getDiscovered())){
            discovered.add(new BigDecimal(0));
        }
        else if(account.getDiscovered().compareTo(account.getDiscoveredMax()) < 0)
        {
            discovered.add(account.getDiscovered().divide(account.getDiscoveredMax())).multiply(new BigDecimal(100));
        }
        model.addAttribute("percent",percent);
        model.addAttribute("discovered",discovered);
        model.addAttribute("account",account);
        model.addAttribute("user",user);
        return "user/index";
    }


}
