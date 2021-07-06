package com.frontend.service;

import com.frontend.Beans.Bank;
import com.frontend.feign.AccountFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class GenerateAccountNumber {

    @Autowired
    private AccountFeign accountFeign;

    public String generateIban(Bank bank, long accountNumber){
        Random rand = new Random();
        String card = "FR76";
        int key = rand.nextInt(99 - 10) + 10;
        card += Integer.toString(bank.getBankCode());
        card += Integer.toString(bank.getBranchCode());
        card += Long.toString(accountNumber);
        card += Integer.toString(key);

        return card;
    }

    public long generateAccountNumber(){
        Random rand = new Random();
        String card = new String();
        long number = 0;

        for (int i = 0; i < 11; i++) {
            int n = rand.nextInt(10) + 0;
            card += Integer.toString(n);
        }
        number = Long.parseLong(card);
            //number = Integer.valueOf(card);
       // }
        return number;
    }

}
