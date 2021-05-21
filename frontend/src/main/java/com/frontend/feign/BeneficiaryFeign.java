package com.frontend.feign;

import com.frontend.Beans.Beneficiary;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "beneficiary")
public interface BeneficiaryFeign {

    @GetMapping("/beneficiaries")
    List<Beneficiary> showAllBeneficiaries();

    @GetMapping("/beneficiary/idUser/{id}")
    List<Beneficiary> showAllBeneficiariesWithIdUser(@PathVariable("id") Integer id);

    @GetMapping("/beneficiary/{id}")
    Beneficiary getBeneficiary(@PathVariable("id") Integer id);

    @GetMapping("/beneficiary/email/{email}")
    Beneficiary getBeneficiaryWithEmail(@PathVariable("email") String email);

    @PostMapping("/beneficiary")
    Beneficiary addBeneficiary(@RequestBody Beneficiary beneficiary);

    @PutMapping("/beneficiary/update/{id}")
    Beneficiary updateBeneficiary(@PathVariable("id") Integer id, @RequestBody Beneficiary update);

    @GetMapping("/beneficiary/delete/{id}")
    void deleteBeneficiary(@PathVariable("id") Integer id);
}
