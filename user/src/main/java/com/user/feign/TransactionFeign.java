package com.user.feign;

import com.user.beans.TransactionBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "transaction",contextId = "transactionController")
public interface TransactionFeign {
    @GetMapping("/transactions")
    List<TransactionBean> showAllTransactions();

    @GetMapping("/transaction/users/{id}")
    List<TransactionBean> showAllTransactionByUser(@PathVariable("id") Integer id);

    @GetMapping("/transaction/id/{id}")
    TransactionBean getTransaction(@PathVariable("id") Integer id);

    @GetMapping("/transaction/email/{sender}/{beneficiary}")
    List<TransactionBean> getAllTransactionByEmail(@PathVariable("sender") String sender,
                                                   @PathVariable("beneficiary") String beneficiary);

    @PostMapping("/transaction")
    TransactionBean createTransaction(@RequestBody TransactionBean transaction);

    @PutMapping("/transaction/update/{id}")
    TransactionBean updateTransaction(@PathVariable("id") Integer id,@RequestBody TransactionBean update);

    @DeleteMapping("/transaction/delete/{id}")
    void deleteTransaction(@PathVariable("id") Integer id);

}
