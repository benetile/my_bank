package com.frontend.feign;

import com.frontend.Beans.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "account",contextId = "accountController")
public interface AccountFeign {

    @GetMapping("/accounts")
    List<Account> showAllAccounts();

    @GetMapping("/account/{id}")
    Account getAccount(@PathVariable("id") Integer id);

    @GetMapping("/account/iban/{iban}")
    Account getAccountWithIban(@PathVariable("iban") String iban);

    @GetMapping("/account/accountNumber/{nb}")
    Account getAccountNumber(@PathVariable("nb") long accountNumber);

    @PostMapping("/account")
    Account createAccount(@RequestBody Account account);

    @PutMapping("/account/{id}")
    Account updateAccount(@PathVariable("id") Integer id, @RequestBody Account update);

    @GetMapping("/account/delete/{id}")
    void deleteAccount(@PathVariable("id") Integer id);
}
