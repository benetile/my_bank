package com.account.controller;

import com.account.model.Account;
import com.account.repository.AccountRepository;
import com.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountService accountService;

    private static String bic = "ABCDEF";

    private static BigDecimal ceilingDefault = BigDecimal.ZERO;
    private static BigDecimal valueDefault = BigDecimal.ZERO;

    @GetMapping("/account/accounts")
    public List<Account> showAllAccounts(){
        return accountRepository.findAll();
    }

    @GetMapping("/account/{id}")
    public Optional<Account> getAccount(@PathVariable("id") Integer id){
        if (accountRepository.existsById(id)){
            return accountRepository.findById(id);
        }
        else
            throw new IllegalArgumentException("Invalid Id : "+id);
    }

    @GetMapping("/account/iban/{iban}")
    public Account getAccountWithIban(@PathVariable("iban") String iban){
        if (accountRepository.findByIban(iban)!= null){
            return accountRepository.findByIban(iban);
        }
        else
            throw new IllegalArgumentException("Invalid Iban : "+iban);
    }

    @GetMapping("/account/accountNumber/{nb}")
    public Account getAccountNumber(@PathVariable("nb") String accountNumber){
        if (accountRepository.findByAccountNumber(accountNumber)!=null){
            return accountRepository.findByAccountNumber(accountNumber);
        }
        else
            throw new IllegalArgumentException("Invalid Account number : "+accountNumber);
    }

    @PostMapping("/account")
    public Account createAccount(@RequestBody Account account, BindingResult result){
        if (!result.hasErrors() && accountRepository.findByIban(account.getIban()) == null){

            account.setDiscovered(valueDefault);
            account.setBic(bic);
            account.setDiscoveredMax(valueDefault);
            account.setSold(valueDefault);
            account.setCeiling(ceilingDefault);
            return accountRepository.save(account);
        }
        else
            throw new IllegalArgumentException("Error cannot");
    }
     @GetMapping("/account/generate")
     public List<String> generateAccountNumber(){
        List<String> liste = new ArrayList<>();
        for (int i=0; i<=6; i++){
            String test = accountService.generateAccountNumber();
            liste.add(test);
        }
        return liste;
     }

    @PutMapping("/account/{id}")
    public Account updateAccount(@PathVariable("id") Integer id, @RequestBody Account update){
        if (accountRepository.existsById(id)){
            update.setIdAccount(id);
            return accountRepository.save(update);
        }
        else
            throw new IllegalArgumentException("Invalid Id : "+id);
    }

    @DeleteMapping("/account/delete/{id}")
    public void deleteAccount(@PathVariable("id") Integer id){
        if (accountRepository.existsById(id)){
            accountRepository.deleteById(id);
        }
        else
            throw new IllegalArgumentException("Invalid id : "+id);
    }

}
