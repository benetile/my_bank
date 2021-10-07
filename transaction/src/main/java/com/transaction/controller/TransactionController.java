package com.transaction.controller;

import com.transaction.feign.UserFeign;
import com.transaction.model.Transaction;
import com.transaction.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private UserFeign userFeign;

    @GetMapping("/transactions")
    public List<Transaction> showAllTransactions(){
        return transactionRepository.findAll();
    }

    /** Modifier le comportement de la méthode pour renvoie la liste des transctions d'un utilisateur*/
    @GetMapping("/transaction/users/{id}")
    public List<Transaction> showAllTransactionByUser(@PathVariable("id") Integer id){
        System.out.println(transactionRepository.findByIdUser(id).size());
        return transactionRepository.findByIdUser(id);
    }

    @GetMapping("/transaction/id/{id}")
    public Optional<Transaction> getTransaction(@PathVariable("id") Integer id){
        if (transactionRepository.existsById(id)){
            return transactionRepository.findById(id);
        }
        else
            throw new IllegalArgumentException("Invalid id : "+id);
    }

    @GetMapping("/transaction/email/{sender}/{beneficiary}")
    public List<Transaction> getAllTransactionByEmail(@PathVariable("sender") String sender,
                                                      @PathVariable("beneficiary") String beneficiary){
        return transactionRepository.findByEmailSenderOrEmailBeneficiary(sender, beneficiary);
    }

    @PostMapping("/transaction")
    public Transaction createTransaction(@RequestBody Transaction transaction, BindingResult result){
        if (!result.hasErrors()){
            return transactionRepository.save(transaction);
        }
        else
            throw new IllegalArgumentException("Error Cannot ");
    }

    @PutMapping("/transaction/update/{id}")
    public Transaction updateTransaction(@PathVariable("id") Integer id,@RequestBody Transaction update){
        Transaction exist = transactionRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid Id : "+id));
        if (transactionRepository.existsById(id)){
            update.setIdTransaction(exist.getIdTransaction());
            return transactionRepository.save(update);
        }
        else
            throw new IllegalArgumentException("Invalid Id : "+id);
    }

    @DeleteMapping("/transaction/delete/{id}")
    public void deleteTransaction(@PathVariable("id") Integer id){
        if (transactionRepository.existsById(id)){
            transactionRepository.deleteById(id);
        }
        else
            throw new IllegalArgumentException("Invalid Id : "+id);
    }

}
