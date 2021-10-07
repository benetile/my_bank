package com.user.feign;

import com.user.beans.TransferBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "transaction",contextId = "transferController")
public interface TransferFeign {

    @GetMapping("/transfers")
    List<TransferBean> showAllTransfers();

    @GetMapping("/transfer/{id}")
    TransferBean getTransfer(@PathVariable("id") Integer id);

    @GetMapping("/transfer/iban/{iban}")
    List<TransferBean> getTransfer(@PathVariable("iban") String iban);

    @GetMapping("/transfer/name/{name}")
    List<TransferBean> getTransferWithName(@PathVariable("name") String name);

    @GetMapping("/transfer/type/{name}/{type}")
    List<TransferBean> getTransferWithNameByTypeTransfer(@PathVariable("name") String name,@PathVariable("type") String type);

    @GetMapping("/transfer/validate/{email}")
    List<TransferBean> getTransferNotValidate(String email);

    @GetMapping("/transfer/invalid")
    List<TransferBean> showAllTransferInvalid();

    @GetMapping("/transfer/my-transfer/{email}")
    List<TransferBean> getTransferByEmail(@PathVariable("email") String email);

    @GetMapping("/transfer/all-transfer/{send}/{beneficiary}")
    List<TransferBean> showAllTransferWithBeneficiary(@PathVariable("send") String send,
                                                         @PathVariable("beneficiary") String beneficiary);
    @PostMapping("/transfer")
    TransferBean fareTransfer(@RequestBody TransferBean transfer);

    @PutMapping("/transfer/update/{id}")
    TransferBean updateTransfer(@PathVariable("id") Integer id, @RequestBody TransferBean update);

    @DeleteMapping("/transfer/delete/{id}")
    void deleteTransfer(@PathVariable("id") Integer id);
}
