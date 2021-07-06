package com.frontend.controller;

import com.frontend.Beans.Account;
import com.frontend.Beans.Beneficiary;
import com.frontend.Beans.User;
import com.frontend.feign.AccountFeign;
import com.frontend.feign.BeneficiaryFeign;
import com.frontend.feign.UserFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

@Controller
public class TransferController {

    @Autowired
    private UserFeign userFeign;
    @Autowired
    private BeneficiaryFeign beneficiaryFeign;
    @Autowired
    private AccountFeign accountFeign;

    @GetMapping("/user/transfer/{email}")
    public String fareTransfer(Principal principal, @PathVariable("email") String email, Model model){
        User user = userFeign.getUserWithEmail(principal.getName());
       // Beneficiary beneficiary = beneficiaryFeign.getBeneficiaryWithEmail(email);
        User userBeneficiary = userFeign.getUserWithEmail(email);
        Account account = user.getAccount();
        Account accountBeneficiary = userBeneficiary.getAccount();
        model.addAttribute("user",user);
        model.addAttribute("account",account);
        model.addAttribute("accountBeneficiary",accountBeneficiary);
        return "transaction/transfer";
    }
}
