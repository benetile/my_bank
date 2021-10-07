package com.beneficiary.controller;

import com.beneficiary.model.Beneficiary;
import com.beneficiary.repository.BeneficiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BeneficiaryController {

    @Autowired
    private BeneficiaryRepository  beneficiaryRepository;

    @GetMapping("/beneficiaries")
    public List<Beneficiary> showAllBeneficiaries(){
        return beneficiaryRepository.findAll();
    }

    @GetMapping("/beneficiary/phone/{phone}")
    public Beneficiary getBeneficiaryWithIdUser(@PathVariable("id") String phone){
        return beneficiaryRepository.findByPhone(phone);
    }

    @GetMapping("/beneficiary/{id}")
    public Optional<Beneficiary> getBeneficiary(@PathVariable("id") Integer id){
        if (beneficiaryRepository.existsById(id)){
            return beneficiaryRepository.findById(id);
        }
        else
            throw new IllegalArgumentException("Invalid id : "+id);
    }

    @GetMapping("/beneficiary/email/{email}")
    public Beneficiary getBeneficiaryWithEmail(@PathVariable("email") String email){
        Beneficiary exist = beneficiaryRepository.findByEmail(email);
        if (exist!=null){
            return beneficiaryRepository.findByEmail(email);
        }
        else
            return null;
            // IllegalArgumentException("Invalid email : "+email);
    }

    @GetMapping("/beneficiary/iban/{iban}")
    public Beneficiary getBeneficiaryWithIban(@PathVariable("iban") String iban){
        return beneficiaryRepository.findByIban(iban);
    }

    @PostMapping("/beneficiary")
    public Beneficiary addBeneficiary(@RequestBody Beneficiary beneficiary, BindingResult result){
        if (!result.hasErrors() && beneficiaryRepository.findByEmail(beneficiary.getEmail()) == null){
            return beneficiaryRepository.save(beneficiary);
        }
        else
            throw new IllegalArgumentException("Error Cannot ");
    }

    @PutMapping("/beneficiary/update/{id}")
    public Beneficiary updateBeneficiary(@PathVariable("id") Integer id, @RequestBody Beneficiary update){
        Beneficiary beneficiary = beneficiaryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid id : "+id));
        if (beneficiaryRepository.existsById(id)){
            update.setIdBeneficiary(id);
            return beneficiaryRepository.save(update);
        }
        else
            throw new IllegalArgumentException("Invalid id : "+id);
    }

    @GetMapping("/beneficiary/delete/{id}")
    public void deleteBeneficiary(@PathVariable("id") Integer id){
        if (beneficiaryRepository.existsById(id)){
            beneficiaryRepository.deleteById(id);
        }
        else
            throw new IllegalArgumentException("Invalid id : "+id);
    }
}
