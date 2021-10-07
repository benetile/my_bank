package com.account.controller;

import com.account.feign.UserFeign;
import com.account.model.Account;
import com.account.model.Bank;
import com.account.repository.AccountRepository;
import com.account.repository.BankRepository;
import com.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
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
    @Autowired
    private BankRepository bankRepository;
    @Autowired
    private UserFeign userFeign;

    /** Utilisation de HTTP CLIENT avec Java 11 **/
    private String url = "http://localhost:8084";
    private static final HttpClient httpClient = HttpClient.newHttpClient();
    /*private static HttpRequest request = HttpRequest.newBuilder()
            //.uri(URI.create("http://localhost:8084/account/accounts"))
            .build();*/

    private static String BIC = "ABCDEF";
    private static BigDecimal ceilingDefault = BigDecimal.ZERO;
    private static BigDecimal ZERO = BigDecimal.ZERO;

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

    @PostMapping("/account/create-account/{id}")
    public Account createAccount(@RequestBody Account account,@PathVariable("id") Integer id){
        Bank bank = bankRepository.getById(id);
        account.setBic(BIC);
        account.setAccountNumber(accountService.generateAccountNumber());
        account.setIban(accountService.generateIban(bank, account.getAccountNumber()));
        account.setSold(ZERO);
        account.setDiscovered(ZERO);

        /** A supprimer si ma methode fonctionne comme je le souhaite
         * account.setDiscoveredMax(ZERO);
        /account.setCeiling(BigDecimal.valueOf(3000));*/

        if (account != null){
            return accountRepository.save(account);
        }
        throw new NullPointerException();

    }

     @GetMapping("/account/generate")
     public List<Account> generateAccountNumber(){
        List<Account> liste = new ArrayList<>();
         Bank bank = bankRepository.findById(1).orElseThrow(()-> new IllegalArgumentException("Invalid Id "));
        for (int i=0; i<=3; i++){
            Account account = new Account();
            account.setDiscovered(ZERO);
            account.setBic(BIC);
            account.setDiscoveredMax(ZERO);
            account.setSold(ZERO);
            account.setCeiling(ceilingDefault);
            account.setAccountNumber(accountService.generateAccountNumber());
            account.setIban(accountService.generateIban(bank, account.getAccountNumber()));
            liste.add(account);
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
