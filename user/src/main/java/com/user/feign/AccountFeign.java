package com.user.feign;

import com.user.beans.AccountBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "account")
public interface AccountFeign {
    @GetMapping("/accounts")
    List<AccountBean> showAllAccounts();

    @GetMapping("/account/{id}")
    AccountBean getAccount(@PathVariable("id") Integer id);

    @GetMapping("/account/iban/{iban}")
    AccountBean getAccountWithIban(@PathVariable("iban") String iban);

    @GetMapping("/account/accountNumber/{nb}")
    AccountBean getAccountNumber(@PathVariable("nb") long accountNumber);

    @PostMapping("/account/create-account/{id}")
    AccountBean createAccount(@RequestBody AccountBean account,@PathVariable("id") Integer id);

    @PutMapping("/account/{id}")
    AccountBean updateAccount(@PathVariable("id") Integer id, @RequestBody AccountBean update);

    @DeleteMapping("/account/delete/{id}")
    void deleteAccount(@PathVariable("id") Integer id);
}
