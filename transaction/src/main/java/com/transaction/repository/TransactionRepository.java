package com.transaction.repository;

import com.transaction.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer> {

    List<Transaction> findByIdUser(Integer id);

    List<Transaction> findByNameSenderOrNameBeneficiary(String s, String b);

    List<Transaction> findByEmailSenderOrEmailBeneficiary(String sender, String beneficiary);

}
