package com.account.repository;

import com.account.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {

    Account findByIban(String iban);

    Account findByAccountNumber(String nb);
}
