package com.beneficiary.repository;

import com.beneficiary.model.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BeneficiaryRepository extends JpaRepository<Beneficiary,Integer> {

    Beneficiary findByEmail(String email);

    Beneficiary findByIdUser(Integer id);
}
