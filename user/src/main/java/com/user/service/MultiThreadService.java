package com.user.service;

import com.user.beans.AccountBean;
import com.user.beans.TransferBean;
import com.user.feign.AccountFeign;
import com.user.feign.TransferFeign;
import com.user.model.User;
import com.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class MultiThreadService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TransferFeign transferFeign;
    @Autowired
    private AccountFeign accountFeign;

    ExecutorService executorService = Executors.newFixedThreadPool(1000);

    public void checkTransferIfNotValidate(TransferBean transferBean){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails =(UserDetails)authentication.getPrincipal();
        User user = userRepository.findByEmail(userDetails.getUsername());
        if (transferBean.getIbanUser().contains(user.getBank().getBranchCode().toString())) {

        }
        else{

        }

    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<TransferBean> transferDisabledList(Principal principal){
        User user = userRepository.findByEmail(principal.getName());
        List<TransferBean>transfers = new ArrayList<>();
        transferFeign.showAllTransferInvalid().parallelStream().forEach(transferBean -> {
            if (transferBean.getIbanUser().contains(user.getBank().getBranchCode().toString())){
                transfers.add(transferBean);
            }
        });
        return transfers;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void checkTransfer(TransferBean transferBean){
        AccountBean account = accountFeign.getAccountWithIban(transferBean.getIbanUser());
        /** le délai pour annuler un virement non valider **/
        long limitDate = 3 * 24 * 60 * 60 * 1000;
        Date now = new Date();
        if (now.getTime() - transferBean.getDateTransfer().getTime() > limitDate && transferBean.getValidate()  == false){
            account.setSold(account.getSold().add(transferBean.getAmount()));
            accountFeign.updateAccount(account.getIdAccount(), account);
            transferFeign.deleteTransfer(transferBean.getIdTransfer());
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void asyncTransferDisabled(TransferBean transferBean){
        TransferBean transfer = new TransferBean();
        Runnable runnable = ()->{
            checkTransfer(transferBean);
            try {
            }catch (Exception e){
                e.printStackTrace();
            }
        };
        executorService.submit(runnable);
    }




}
