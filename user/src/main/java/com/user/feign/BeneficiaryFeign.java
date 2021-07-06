package com.user.feign;

import com.user.beans.BeneficiaryBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "beneficiary")
public interface BeneficiaryFeign {
    @GetMapping("/beneficiaries")
    List<BeneficiaryBean> showAllBeneficiaries();

    @GetMapping("/beneficiary/idUser/{id}")
    List<BeneficiaryBean> showAllBeneficiariesWithIdUser(@PathVariable("id") Integer id);

    @GetMapping("/beneficiary/{id}")
    BeneficiaryBean getBeneficiary(@PathVariable("id") Integer id);

    @GetMapping("/beneficiary/email/{email}")
    BeneficiaryBean getBeneficiaryWithEmail(@PathVariable("email") String email);

    @PostMapping("/beneficiary")
    BeneficiaryBean addBeneficiary(@RequestBody BeneficiaryBean beneficiary);

    @PutMapping("/beneficiary/update/{id}")
    BeneficiaryBean updateBeneficiary(@PathVariable("id") Integer id, @RequestBody BeneficiaryBean update);

    @GetMapping("/beneficiary/delete/{id}")
    void deleteBeneficiary(@PathVariable("id") Integer id);

}
