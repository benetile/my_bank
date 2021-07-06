package com.frontend.feign;

import com.frontend.Beans.Bank;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "account",contextId = "bankController")
public interface BankFeign {

    @GetMapping("/banks")
    List<Bank> showAllBanks();

    @GetMapping("/bank/{id}")
    Bank getBank(@PathVariable("id") Integer id);

    @GetMapping("/bank/bank-code/{bc}")
    Bank getBankWithBankCode(@PathVariable("bc") Integer bc);

    @GetMapping("/bank/branch-code/{bc}")
    Bank getBankWithBranchCode(@PathVariable("bc") Integer bc);

    @PostMapping("/bank")
    Bank createBank(@RequestBody Bank bank);

    @PutMapping("/bank/update/{id}")
    Bank updateBank(@PathVariable("id") Integer id, @RequestBody Bank update);

    @GetMapping("/bank/delete/{id}")
    void deleteBank(@PathVariable("id") Integer id);
}
