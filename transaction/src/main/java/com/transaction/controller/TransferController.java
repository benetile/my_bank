package com.transaction.controller;

import com.transaction.model.Transfer;
import com.transaction.repository.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class TransferController {

    @Autowired
    private TransferRepository transferRepository;

    @GetMapping("/transfers")
    public List<Transfer> showAllTransfers(){
        return transferRepository.findAll();
    }

    @GetMapping("/transfer/{id}")
    public Optional<Transfer> getTransfer(@PathVariable("id") Integer id){
        if (transferRepository.existsById(id)){
            return transferRepository.findById(id);
        }
        else
           throw new IllegalArgumentException("Invalid Id "+id);
    }

    @GetMapping("/transfer/iban/{iban}")
    public List<Transfer> getTransfer(@PathVariable("iban") String iban){
        if (transferRepository.findByIbanUserOrIbanBeneficiary(iban,iban)!=null){
            return transferRepository.findByIbanUserOrIbanBeneficiary(iban,iban);
        }
        else
            return null;
    }

    @GetMapping("/transfer/name/{name}")
    public List<Transfer> getTransferWithName(@PathVariable("name") String name){
        if (transferRepository.findByNameSenderOrNameBeneficiary(name,name)!=null){
            return transferRepository.findByNameSenderOrNameBeneficiary(name,name);
        }
        else
            return null;
    }
    @GetMapping("/transfer/type/{name}/{type}")
    public List<Transfer> getTransferWithNameByTypeTransfer(@PathVariable("name") String name,@PathVariable("type") String type){
        if (transferRepository.findByNameSenderAndTransferType(name, type)!=null){
            return transferRepository.findByNameSenderAndTransferType(name, type);
        }
        else
            return null;
    }
    @GetMapping("/transfer/validate/{email}")
    public List<Transfer> getTransferNotValidate(String email){
        return transferRepository.findByEmailSenderAndValidateFalse(email);
    }

    @GetMapping("/transfer/invalid")
    public List<Transfer> showAllTransferInvalid(){
        return transferRepository.findByValidateFalse();
    }

    @GetMapping("/transfer/my-transfer/{email}")
    public List<Transfer> getTransferByEmail(@PathVariable("email") String email){
        return transferRepository.findByEmailSenderOrEmailBeneficiary(email,email);
    }

    @GetMapping("/transfer/all-transfer/{send}/{beneficiary}")
    public List<Transfer> showAllTransferWithBeneficiary(@PathVariable("send") String send,
                                                         @PathVariable("beneficiary") String beneficiary){
        return transferRepository.findByEmailSenderAndEmailBeneficiary(send, beneficiary);
    }

    @PostMapping("/transfer")
    public Transfer fareTransfer(@RequestBody Transfer transfer, BindingResult result){
        if (!result.hasErrors()){
            transfer.setDateTransfer(new Date());
            transfer.setValidate(false);
            return transferRepository.save(transfer);
        }
        else 
            throw new IllegalArgumentException("Error cannot");
    }
    
    @PutMapping("/transfer/update/{id}")
    public Transfer updateTransfer(@PathVariable("id") Integer id, @RequestBody Transfer update){
        Transfer exist = transferRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Id : "+id));
        if (transferRepository.existsById(id)){
            update.setIdTransfer(exist.getIdTransfer());
            return transferRepository.save(update);
        }
        else
            throw new IllegalArgumentException("Invalid Id : "+id);
    }

    @DeleteMapping("/transfer/delete/{id}")
    public void deleteTransfer(@PathVariable("id") Integer id){
        if (transferRepository.existsById(id)){
            transferRepository.deleteById(id);
        }
        else
            throw new IllegalArgumentException("Invalid id : "+id);
    }
}
