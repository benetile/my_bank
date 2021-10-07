package com.account.service;

import com.account.model.Bank;
import com.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public String generateAccountNumber(){
        Random random = new Random();
        String card = new String();
        for (int i=1; i<=11;i++){
            int n = random.nextInt(10) + 0;
            card += Integer.toString(n);
        }
        return card;
    }

    public String generateIban(Bank bank, String accountNumber){
        Random random = new Random();
        String iban = "FR76";
        String key = new String();

        /** Generate Key for Iban with boucle For */
        for (int i=0;i<2;i++){
            int n = random.nextInt(10) +0;
            key+= Integer.toString(n);
        }
        iban += Integer.toString(bank.getBankCode());
        iban += Integer.toString(bank.getBranchCode());
        iban += accountNumber;//Long.toString(accountNumber);
        iban += key;
        return iban;
    }

    public int generateCard(String cardType){
        int prefix;
        long number;
        Random rand = new Random();
        if (cardType.equals("VISA")){
            prefix = 4973;
        }
        else if (cardType.equals("MASTER")){
            prefix =5139;
        }
        else if(cardType.equals("VISA_ELECTRON")){
            prefix = 4026;
        }
        number =rand.nextInt(100);
        return 0;
    }
}
