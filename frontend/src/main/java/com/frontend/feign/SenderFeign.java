package com.frontend.feign;

import com.frontend.Beans.Sender;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "sender")
public interface SenderFeign {

    @GetMapping("/senders")
    List<Sender> showAllSender();

    @GetMapping("/senders/idUser/{id}")
    List<Sender> getSenderWithIdUser(@PathVariable("id") Integer id);

    @GetMapping("/sender/{id}")
    Sender getSender(@PathVariable("id") Integer id);

    @PostMapping("/sender")
    Sender addSender(@RequestBody Sender sender, BindingResult result);

    @PutMapping("/sender/update/{id}")
    Sender updateSender(@PathVariable("id") Integer id, @RequestBody Sender update);

    @GetMapping("/sender/delete/{id}")
    void deleteSender(@PathVariable("id") Integer id);
}
