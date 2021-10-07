package com.user.feign;

import com.user.beans.BankBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "account",contextId = "bankController")
public interface BankFeign {

    @GetMapping("/banks")
    List<BankBean> showAllBanks();

    @GetMapping("/bank/{id}")
    BankBean getBank(@PathVariable("id") Integer id);

    @GetMapping("/bank/bank-code/{bc}")
    BankBean getBankWithBankCode(@PathVariable("bc") Integer bc);

    @GetMapping("/bank/branch-code/{bc}")
    BankBean getBankWithBranchCode(@PathVariable("bc") Integer bc);

    @PostMapping("/bank")
    BankBean createBank(@RequestBody BankBean bank);

    @PutMapping("/bank/update/{id}")
    BankBean updateBank(@PathVariable("id") Integer id, @RequestBody BankBean update);

    @GetMapping("/bank/delete/{id}")
    void deleteBank(@PathVariable("id") Integer id);
}
