package com.account.service;

import com.account.controller.AccountController;
import com.account.model.Bank;
import com.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AccountService {

    @Autowired
    private AccountController accountController;

    @Autowired
    private AccountRepository accountRepository;

    public String generateAccountNumber(){
        Random random = new Random();
        String card = new String();
        long number = 0;

        for (int i=1; i<=11;i++){
            int n = random.nextInt(10) + 0;
            card += Integer.toString(n);
        }
        //number = Long.parseLong(card);
        return card;
    }

    public String generateIban(Bank bank, long accountNumber){
        Random random = new Random();
        String iban = "FR76";
        String k = new String();

        /** Generate Key for Iban with boucle For */
        for (int i=0;i<3;i++){
            int n = random.nextInt(10) +0;
            k+= Integer.toString(n);
        }
        int key = Integer.parseInt(k);
        iban += Integer.toString(bank.getBankCode());
        iban += Integer.toString(bank.getBranchCode());
        iban += Long.toString(accountNumber);
        iban += Integer.toString(key);
        return iban;
    }
}
