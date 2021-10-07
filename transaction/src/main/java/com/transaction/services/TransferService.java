package com.transaction.services;

import com.transaction.model.Transfer;
import com.transaction.repository.TransactionRepository;
import com.transaction.repository.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class TransferService {

    ExecutorService executorService = Executors.newFixedThreadPool(1000);

    @Autowired
    private TransferRepository transferRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    public void checkTransferIfNotValidate(Transfer transfer){

    }

    public void asyncCheckTransferIfNotValidate(Transfer transfer){
        Runnable runnable =() ->{
            try {

            }
            catch (Exception e){
                e.printStackTrace();
            }
        };
        executorService.submit(runnable);
    }
}
