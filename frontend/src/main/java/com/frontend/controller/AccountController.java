package com.frontend.controller;

import com.frontend.Beans.Account;
import com.frontend.Beans.Bank;
import com.frontend.Beans.Beneficiary;
import com.frontend.Beans.User;
import com.frontend.feign.AccountFeign;
import com.frontend.feign.BankFeign;
import com.frontend.feign.BeneficiaryFeign;
import com.frontend.feign.UserFeign;
import com.frontend.service.GenerateAccountNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.security.Principal;

@Controller
public class AccountController {

    @Autowired
    private UserFeign userFeign;
    @Autowired
    private AccountFeign accountFeign;
    @Autowired
    private BeneficiaryFeign beneficiaryFeign;
    @Autowired
    private BankFeign bankFeign;
    @Autowired
    private GenerateAccountNumber generateAccountNumber;

    @GetMapping("/admin/account/{id}")
    public String getAccount(@PathVariable("id") Integer id, Model model){
        Account account = accountFeign.getAccount(id);
        model.addAttribute("account",account);
        return null;
    }
    @GetMapping("/admin/account")
    public String accountPage(Model model){
        model.addAttribute("account",new Account());
        return null;
    }

    @GetMapping("/admin/createAccount/{iban}/{id}")
    public String createAccount(@PathVariable("iban") String iban, @PathVariable("id") Integer id,
                                Principal principal, Model model){

        User admin = userFeign.getUserWithEmail(principal.getName());
        Account account = accountFeign.getAccountWithIban(iban);
        User user = userFeign.getUser(id);
        if (user.getAccount()== null){
            user.setAccount(account);
            userFeign.updateUser(user.getIdUser(), user);
            model.addAttribute("message","The operation was successful");
            model.addAttribute("user",admin);
            model.addAttribute("userSave",user);
            return "admin/message";
        }
        return "";
    }

    @GetMapping("/admin/attributeAccount/{email}")
    public String attributeAccount(@PathVariable("email") String email, Principal principal, Model model){
        User admin = userFeign.getUserWithEmail(principal.getName());
        User user = userFeign.getUserWithEmail(email);
        Bank bank = user.getBank();
        Account account = new Account();
        if(user!=null) {
            Beneficiary beneficiary = new Beneficiary(user.getFirstname(), user.getLastname(), user.getEmail(), user.getIdUser());
            account.setFirstname(user.getFirstname());
            account.setLastname(user.getLastname());
            account.setAccountNumber(generateAccountNumber.generateAccountNumber());
            account.setIban(generateAccountNumber.generateIban(bank, account.getAccountNumber()));
            beneficiaryFeign.addBeneficiary(beneficiary);
            accountFeign.createAccount(account);
            userFeign.updateUser(user.getIdUser(), user);
            model.addAttribute("user",admin);
            model.addAttribute("userSave",user);
            model.addAttribute("account",account);
            return "account/createAccount";
        }
        return "redirect:/admin/index";
    }


    @GetMapping("/user/monCompte")
    public String createAccount(Principal principal, Model model){
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

        return null;
    }
}
