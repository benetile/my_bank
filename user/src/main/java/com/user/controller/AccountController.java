package com.user.controller;

import com.user.beans.*;
import com.user.exceptions.InvalidIdException;
import com.user.feign.AccountFeign;
import com.user.feign.BankFeign;
import com.user.feign.BeneficiaryFeign;
import com.user.feign.TransactionFeign;
import com.user.model.User;
import com.user.repository.UserRepository;
import com.user.service.AuthenticateUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;

@RestController
public class AccountController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountFeign accountFeign;
    @Autowired
    private BankFeign bankFeign;
    @Autowired
    private AuthenticateUser authenticateUser;
    @Autowired
    private BeneficiaryFeign beneficiaryFeign;
    @Autowired
    private TransactionFeign transactionFeign;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/account/attribute-account/{id}/{ceiling}/{discoveredMax}")
    public AccountBean addNewAccount(@PathVariable("id") Integer id, @PathVariable("ceiling") BigDecimal ceiling,
                              @PathVariable("discoveredMax") BigDecimal discoveredMax, Principal principal){
        AccountBean account = new AccountBean();
        AccountBean accountUser = new AccountBean();
        BeneficiaryBean beneficiary = new BeneficiaryBean();
        User userPrincipal = userRepository.findByEmail(principal.getName());
        if (userPrincipal.getRole().equals("ADMIN")){
            User user = userRepository.findById(id).orElseThrow(()->new InvalidIdException("Invalid Id : "+id));
            account.setFirstname(user.getFirstname());
            account.setLastname(user.getLastname());
            account.setCeiling(ceiling);
            account.setDiscoveredMax(discoveredMax);
            accountUser = accountFeign.createAccount(account,userPrincipal.getBank().getIdBank());
            user.setBank(userPrincipal.getBank());
            user.setAccount(accountUser);
            userRepository.save(user);
            beneficiary = beneficiaryFeign.getBeneficiaryWithEmail(user.getEmail());
            beneficiary.setIban(user.getAccount().getIban());
            beneficiaryFeign.updateBeneficiary(beneficiary.getIdBeneficiary(), beneficiary);
            return user.getAccount();
        }else {
            throw new IllegalArgumentException("Error Cannot");
        }

    }

    @GetMapping("/account/details/{id}")
    public AccountBean getDetailsAccount(@PathVariable("id") Integer id){
        User user = userRepository.findById(id).orElseThrow(()-> new InvalidIdException("Invalid id : "+id));
        return user.getAccount();
    }

    /*** Méthode pour modifier ou mettre à jour les informations d'un compte ***/
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/account/update-account/{id}")
    public AccountBean updateAccount(@PathVariable("id") Integer id, @RequestBody AccountBean update){
      // User userPrincipal = userRepository.findByEmail(principal.getName());
       User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Id : "+id));
       AccountBean account = user.getAccount();
       if (user.getAccount()!=null){
           update.setIdAccount(account.getIdAccount());
           accountFeign.updateAccount(account.getIdAccount(),update);
           user.setAccount(update);
           userRepository.save(user);
           return account;
       }
       throw new IllegalArgumentException("Invalid Id : "+id);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/account/fundAccount")
    public AccountBean fundAccountUser(@RequestBody Card card, Principal principal){
        User user = userRepository.findByEmail(principal.getName());
        AccountBean account = user.getAccount();
        account.setSold(account.getSold().add(card.getSold()));
        long numberTransaction = transactionFeign.showAllTransactionByUser(user.getIdUser()).size() +1;
        accountFeign.updateAccount(account.getIdAccount(),account);
        userRepository.save(user);
        TransactionBean transaction = new TransactionBean(user.getIdUser(), card.getName(),"Card", user.getFirstname()+ " " +user.getLastname(), user.getEmail(),"Approvisonner", "Approvisonnement", card.getSold(), numberTransaction, new Date());
        transactionFeign.createTransaction(transaction);
        return account;
    }
}
