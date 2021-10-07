package com.user.controller;

import com.user.beans.TransactionBean;
import com.user.feign.TransactionFeign;
import com.user.model.User;
import com.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class TransactionController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TransactionFeign transactionFeign;

    /**@GetMapping("/transactions")
    public List<TransactionBean> getAllTransactionForUser(Principal principal){
        User user = userRepository.findByEmail(principal.getName());
        List<TransactionBean> transactions= transactionFeign.showAllTransactionByUser(user.getIdUser());
        return transactions;
    }*/

    @GetMapping("/transactions")
    public List<TransactionBean> getAllTransactionForUser(Principal principal){
        User user = userRepository.findByEmail(principal.getName());
        //List<TransactionBean> transactions = transactionFeign.getAllTransactionByEmail()
        return transactionFeign.getAllTransactionByEmail(user.getEmail(), user.getEmail());
    }


}
