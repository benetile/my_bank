package com.user.service;

import com.user.beans.BankBean;
import com.user.feign.AccountFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AccountService {

    @Autowired
    private AccountFeign accountFeign;

    public String generateAccountNumber(){
        Random random = new Random();
        String card = new String();
        for (int i=1; i<=11;i++){
            int n = random.nextInt(10) + 0;
            card += Integer.toString(n);
        }
        return card;
    }

    public String generateIban(BankBean bank, String accountNumber){
        Random random = new Random();
        String iban = "FR76";
        String key = new String();

        /** Generate Key for Iban with boucle For */
        for (int i=0;i<3;i++){
            int n = random.nextInt(10) +0;
            key+= Integer.toString(n);
        }
        iban += Integer.toString(bank.getBankCode());
        iban += Integer.toString(bank.getBranchCode());
        iban += accountNumber;//Long.toString(accountNumber);
        iban += key;
        return iban;
    }
}
