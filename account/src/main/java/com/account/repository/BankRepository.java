package com.account.repository;

import com.account.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends JpaRepository<Bank,Integer> {

    Bank findByBankCode(Integer bankCode);

    Bank findByBranchCode(Integer branchCode);

}
