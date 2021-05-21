package com.sender.controller;

import com.sender.model.Sender;
import com.sender.repository.SenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class SenderController {

    @Autowired
    private SenderRepository senderRepository;

    @GetMapping("/senders")
    public List<Sender> showAllSender(){
        return senderRepository.findAll();
    }

    @GetMapping("/senders/idUser/{id}")
    public List<Sender> getSenderWithIdUser(@PathVariable("id") Integer id){
        return senderRepository.findByIdUser(id);
    }

    @GetMapping("/sender/{id}")
    public Optional<Sender> getSender(@PathVariable("id") Integer id){
        if (senderRepository.existsById(id)){
            return senderRepository.findById(id);
        }
        else
            throw new IllegalArgumentException("Invalid Id : "+id);
    }

    @PostMapping("/sender")
    public Sender addSender(@RequestBody Sender sender, BindingResult result){
        if (!result.hasErrors()){
            return senderRepository.save(sender);
        }
        else
            throw new IllegalArgumentException("Error Cannot ");
    }

    @PutMapping("/sender/update/{id}")
    public Sender updateSender(@PathVariable("id") Integer id, @RequestBody Sender update){
        Sender exist = senderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Id : " +id));
        if (senderRepository.existsById(id)){
            update.setIdSender(id);
            update.setEmail(exist.getEmail());
            return senderRepository.save(update);
        }
        else
            throw new IllegalArgumentException("Invalid Id : "+id);
    }

    @GetMapping("/sender/delete/{id}")
    public void deleteSender(@PathVariable("id") Integer id){
        if (senderRepository.existsById(id)){
            senderRepository.deleteById(id);
        }
        else
            throw new IllegalArgumentException("Invalid Id : "+id);
    }
}
