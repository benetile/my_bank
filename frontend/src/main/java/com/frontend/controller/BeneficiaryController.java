package com.frontend.controller;

import com.frontend.Beans.Account;
import com.frontend.Beans.Beneficiary;
import com.frontend.Beans.User;
import com.frontend.feign.BeneficiaryFeign;
import com.frontend.feign.UserFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.security.Principal;

@Controller
public class BeneficiaryController {

    @Autowired
    private UserFeign userFeign;
    @Autowired
    private BeneficiaryFeign beneficiaryFeign;

    @GetMapping("/user/mesBeneficiaires")
    public String showAllBeneficiary(Principal principal,Model model){
        User user = userFeign.getUserWithEmail(principal.getName());
        Account account = user.getAccount();
        BigDecimal percent = ((new BigDecimal("0").divide(account.getCeiling())).multiply(new BigDecimal(100)));
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
        model.addAttribute("user", user);
        return "beneficiary/beneficiaryList";
    }

    @PostMapping("/user/search")
    public String searchBeneficiary(Principal principal,@RequestParam("email") String email,Model model){
        User user = userFeign.getUserWithEmail(principal.getName());
        User beneficiary = userFeign.getUserWithEmail(email);
        Account account = user.getAccount();
        BigDecimal percent = ((new BigDecimal("0").divide(account.getCeiling())).multiply(new BigDecimal(100)));
        BigDecimal discovered = new BigDecimal("0");
        if (account.getDiscoveredMax().equals(account.getDiscovered())){
            discovered.add(new BigDecimal(0));
        }
        else if(account.getDiscovered().compareTo(account.getDiscoveredMax()) < 0)
        {
            discovered.add(account.getDiscovered().divide(account.getDiscoveredMax())).multiply(new BigDecimal(100));
        }
        model.addAttribute("email",email);
        model.addAttribute("percent",percent);
        model.addAttribute("discovered",discovered);
        model.addAttribute("account",account);
        model.addAttribute("user",user);
        model.addAttribute("beneficiary",beneficiary);
        return "beneficiary/beneficiaryDetails";
    }

    @GetMapping("/user/beneficiaire/{id}")
    public String getBeneficiary(Principal principal,@PathVariable("id") Integer id, Model model){
        User user = userFeign.getUserWithEmail(principal.getName());
        User us = userFeign.getUser(id);
        Beneficiary beneficiary = beneficiaryFeign.getBeneficiaryWithIdUser(id);
        if(!user.getBeneficiaries().stream().anyMatch(beneficiary1 -> beneficiary1.getEmail().equals(beneficiary.getEmail()))){
            user.getBeneficiaries().add(beneficiary);
            userFeign.updateUser(user.getIdUser(),user);
            return "redirect:/user/mesBeneficiaires";
        }
        return "redirect:/user/mesBeneficiaires";
    }

    @GetMapping("/user/detailsBeneficiaire/{id}")
    public String detailsBeneficiary(@PathVariable("id") Integer id,Principal principal,Model model){
        User user = userFeign.getUserWithEmail(principal.getName());
        Beneficiary beneficiary = beneficiaryFeign.getBeneficiary(id);
        User userBeneficiary = userFeign.getUser(beneficiary.getIdUser());
        Account account = user.getAccount();
        Account accountBeneficiary = userBeneficiary.getAccount();
        BigDecimal percent = ((new BigDecimal("0").divide(account.getCeiling())).multiply(new BigDecimal(100)));
        BigDecimal discovered = new BigDecimal("0");
        if (account.getDiscoveredMax().equals(account.getDiscovered())){
            discovered.add(new BigDecimal(0));
        }
        else if(account.getDiscovered().compareTo(account.getDiscoveredMax()) < 0)
        {
            discovered.add(account.getDiscovered().divide(account.getDiscoveredMax())).multiply(new BigDecimal(100));
        }

        model.addAttribute("accountBeneficiary",accountBeneficiary);
        model.addAttribute("percent",percent);
        model.addAttribute("discovered",discovered);
        model.addAttribute("account",account);
        model.addAttribute("user",user);
        model.addAttribute("beneficiary",beneficiary);
        return "beneficiary/beneficiary";
    }

}
