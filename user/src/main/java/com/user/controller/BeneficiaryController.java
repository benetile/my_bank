package com.user.controller;

import com.user.beans.BeneficiaryBean;
import com.user.exceptions.InvalidIdException;
import com.user.feign.BeneficiaryFeign;
import com.user.model.User;
import com.user.repository.UserRepository;
import com.user.service.AuthenticateUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

@RestController("/beneficiary")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class BeneficiaryController {

    @Autowired
    private BeneficiaryFeign beneficiaryFeign;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticateUser authenticateUser;

    @GetMapping("/all")
    public List<BeneficiaryBean> showAllBeneficiaries(){
        return beneficiaryFeign.showAllBeneficiaries();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public BeneficiaryBean getBeneficiary(@PathVariable("id") Integer id){
        if (beneficiaryFeign.getBeneficiary(id)!= null){
            return beneficiaryFeign.getBeneficiary(id);
        }
        throw new InvalidIdException("");
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/add")
    public BeneficiaryBean createdBeneficiary(@RequestBody BeneficiaryBean beneficiary,Principal principal){
        User user = userRepository.findByEmail(principal.getName());
        if (beneficiaryFeign.getBeneficiaryWithEmail(beneficiary.getEmail()) == null){
            beneficiaryFeign.addBeneficiary(beneficiary);
            return beneficiary;
        }
        throw new IllegalArgumentException("Une erreur s'est produite ");
    }

    @GetMapping("/add/{email}")
    public Boolean confirmAdd(@PathVariable("email") String email, Principal principal){
        BeneficiaryBean beneficiary = beneficiaryFeign.getBeneficiaryWithEmail(email);
        User user = userRepository.findByEmail(principal.getName());
        user.getBeneficiaries().add(beneficiary);
        userRepository.save(user);
        return true;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/beneficiaries")
    public List<BeneficiaryBean> getBeneficiariesForUser(Principal principal){
        User user = userRepository.findByEmail(principal.getName());
        return user.getBeneficiaries();
    }

    @GetMapping("/beneficiary/{email}")
    public BeneficiaryBean searchBeneficiary(@PathVariable("email") String email,Principal principal){
        BeneficiaryBean beneficiaryPrincipal = beneficiaryFeign.getBeneficiaryWithEmail(principal.getName());
        if (beneficiaryFeign.getBeneficiaryWithEmail(email)!=null && !beneficiaryPrincipal.getEmail().equals(email)) {
            return beneficiaryFeign.getBeneficiaryWithEmail(email);
        }
        throw new IllegalArgumentException("Impossible");
    }

    @GetMapping("/beneficiary/id/{id}")
    public BeneficiaryBean getOneBeneficiary(@PathVariable("id") Integer id){
        return beneficiaryFeign.getBeneficiary(id);
    }

    @GetMapping("/delete-beneficiary/{id}")
    public Boolean withdrawBeneficiary(@PathVariable ("id") Integer id, Principal principal) {
        User user = userRepository.findByEmail(principal.getName());
        BeneficiaryBean beneficiary = beneficiaryFeign.getBeneficiary(id);
        for (Iterator<BeneficiaryBean> items = user.getBeneficiaries().iterator(); items.hasNext();) {
            BeneficiaryBean bean = items.next();
            if (bean.getEmail().equals(beneficiary.getEmail())) {
                items.remove();
                userRepository.save(user);
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

}
