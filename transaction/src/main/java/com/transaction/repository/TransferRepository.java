package com.transaction.repository;

import com.transaction.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferRepository extends JpaRepository<Transfer,Integer> {

    List<Transfer> findByIbanUserOrIbanBeneficiary(String iban, String ibanTwo);

    List<Transfer> findByNameSenderOrNameBeneficiary(String name, String nameTwo);

    List<Transfer> findByNameSenderAndTransferType(String name, String type);

    List<Transfer> findByValidateFalse();
}
