package com.account.controller;

import com.account.model.Bank;
import com.account.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class BankController {

    @Autowired
    private BankRepository bankRepository;

    @GetMapping("/bank/banks")
    public List<Bank> showAllBanks(){
        return bankRepository.findAll();
    }

    private static Integer bankCode = 15005;

    @GetMapping("/bank/{id}")
    public Optional<Bank> getBank(@PathVariable("id") Integer id){
        if (bankRepository.existsById(id)){
            return bankRepository.findById(id);
        }
        else
            throw new IllegalArgumentException("Invalid id : "+id);
    }

    @GetMapping("/bank/bank-code/{bc}")
    public Bank getBankWithBankCode(@PathVariable("bc") Integer bc){
        if (bankRepository.findByBankCode(bc)!=null){
            return bankRepository.findByBankCode(bc);
        }
        else
            throw new IllegalArgumentException("Invalid Bank code : "+bc);
    }

    @GetMapping("/bank/branch-code/{bc}")
    public Bank getBankWithBranchCode(@PathVariable("bc") Integer bc){
        if (bankRepository.findByBranchCode(bc)!=null){
            return bankRepository.findByBranchCode(bc);
        }
        else
            throw new IllegalArgumentException("Invalid Branch code : "+bc);
    }

    @PostMapping("/bank")
    public Bank createBank(@RequestBody Bank bank, BindingResult result){
        if (!result.hasErrors() && bankRepository.findByBranchCode(bank.getBranchCode())==null){
            bank.setBankCode(bankCode);
            return bankRepository.save(bank);
        }
        else
            throw new IllegalArgumentException("Error cannot ");
    }

    @PutMapping("/bank/update/{id}")
    public Bank updateBank(@PathVariable("id") Integer id, @RequestBody Bank update){
        Bank bank = bankRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid id : "+id));
        if (bankRepository.existsById(id)){
            update.setIdBank(id);
            update.setBankCode(bankCode);
            return bankRepository.save(update);
        }
        else
            throw new IllegalArgumentException("Invalid Id : "+id);
    }
    @DeleteMapping("/bank/delete/{id}")
    public void deleteBank(@PathVariable("id") Integer id){
        if (bankRepository.existsById(id)){
            bankRepository.deleteById(id);
        }
        else
            throw new IllegalArgumentException("Invalid id : "+id);
    }

}
