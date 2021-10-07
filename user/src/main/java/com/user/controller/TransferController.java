package com.user.controller;

import com.user.beans.AccountBean;
import com.user.beans.BeneficiaryBean;
import com.user.beans.TransactionBean;
import com.user.beans.TransferBean;
import com.user.exceptions.InvalidIdException;
import com.user.feign.AccountFeign;
import com.user.feign.BeneficiaryFeign;
import com.user.feign.TransactionFeign;
import com.user.feign.TransferFeign;
import com.user.model.User;
import com.user.repository.UserRepository;
import com.user.service.MultiThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;
import java.util.List;

@RestController
public class TransferController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TransferFeign transferFeign;
    @Autowired
    private BeneficiaryFeign beneficiaryFeign;
    @Autowired
    private TransactionFeign transactionFeign;
    @Autowired
    private AccountFeign accountFeign;
    @Autowired
    private MultiThreadService threadService;

    @PostMapping("/transfer")
    public TransferBean fareTransfer(@RequestBody TransferBean transferBean, Principal principal){
        User user = userRepository.findByEmail(principal.getName());
        AccountBean account = user.getAccount();
        if ((account.getSold().add(account.getDiscoveredMax())).compareTo(transferBean.getAmount())>=0){
           account.setSold(account.getSold().subtract(transferBean.getAmount()));
           transferFeign.fareTransfer(transferBean);
           accountFeign.updateAccount(account.getIdAccount(),account);
           return transferBean;
        }
        throw
               new InvalidIdException("Une erreur s'est produite lors de l'éxecution");
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/transfer/validate/{id}")
    public TransferBean validateTransfer(@PathVariable("id") Integer id){
        String typeTransaction ="Virement";
        TransferBean transferBean = transferFeign.getTransfer(id);
        /** On recupere l'éxpediteur grâce à son email et le bénéficiaire grâce à son IBAN **/
        User user = userRepository.findByEmail(transferBean.getEmailSender());
        BeneficiaryBean beneficiary = beneficiaryFeign.getBeneficiaryWithIban(transferBean.getIbanBeneficiary());
        long number = transactionFeign.showAllTransactionByUser(user.getIdUser()).size();

        /** on recupere le compte du beneficiaire si celui est client de notre banque afin de transferer l'argent */
        AccountBean accountBeneficiary = new AccountBean();
        if (accountFeign.getAccountWithIban(transferBean.getIbanBeneficiary())!= null){
            transferBean.setValidate(true);
            accountBeneficiary.setSold(accountBeneficiary.getSold().add(transferBean.getAmount()));
            accountFeign.updateAccount(accountBeneficiary.getIdAccount(),accountBeneficiary);
            transferBean.setDateValidateTransfer(new Date());
            transferFeign.updateTransfer(id,transferBean);
        } else {
            transferBean.setValidate(true);
            transferBean.setDateValidateTransfer(new Date());
            transferFeign.updateTransfer(id,transferBean);
        }
        TransactionBean transaction = new TransactionBean(user.getIdUser(),user.getFirstname()+" "+user.getLastname(), user.getEmail(), beneficiary.getFirstname()+" "+beneficiary.getLastname(),
                beneficiary.getEmail(), typeTransaction,transferBean.getDescription(),transferBean.getAmount(),number+1,new Date());
        transactionFeign.createTransaction(transaction);
        return transferBean;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/transfer/cancel/{id}")
    public void cancelTransfer(@PathVariable("id")Integer id){
        TransferBean transfer = transferFeign.getTransfer(id);
        AccountBean account = accountFeign.getAccountWithIban(transfer.getIbanUser());
        if (transfer.getValidate().equals(false)){
            account.setSold(account.getSold().add(transfer.getAmount()));
            accountFeign.updateAccount(account.getIdAccount(),account);
            transferFeign.deleteTransfer(transfer.getIdTransfer());
        }
        throw new InvalidIdException("Une Erreur s'est produite lors de la suppression du virement");
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/transfer/disabled")
    public void showAllTransferDisabled(Principal principal) {
        User user = userRepository.findByEmail(principal.getName());
        List<TransferBean> transfers = threadService.transferDisabledList(principal);
        for (TransferBean transfer : transfers){
            threadService.asyncTransferDisabled(transfer);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/transfer/check/{id}")
    public TransferBean showTransfer(@PathVariable("id") Integer id){
        return transferFeign.getTransfer(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/checkTransferNotValidate")
    public void checkIfTransferIfNotValidate(Principal principal){
        User user = userRepository.findByEmail(principal.getName());

    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/transfers")
    public List<TransferBean> showTransferNotValidate(Principal principal){
        List<TransferBean> transferBeans = threadService.transferDisabledList(principal);
        return transferBeans;
    }
}
