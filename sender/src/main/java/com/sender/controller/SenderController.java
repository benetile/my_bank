package com.sender.controller;

import com.sender.model.Sender;
import com.sender.repository.SenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

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

    @GetMapping("/test")
    public int calculateTotalPrice(){
        int [] prices = {100,50,60,80,15,200};
        Arrays.sort(prices);
        int p = 15;
        int total = 0;

        int plusCher = prices[prices.length-1];
        int red = (int) (Math.floor(plusCher * p)/100);
        for (int price : prices){
            total += price;
        }
        total-=red;
        return total;
    }

    @GetMapping("/test2")
    public double approx(){
        double [][] rands = new double[100000][2];
        for (int i=0;i< rands.length;i++){
            rands[i][0]= Math.random();
            rands[i][1]= Math.random();
        }
        System.out.println(rands);

        int n = 0;
        int total = rands.length;
        for (double [] rand : rands){
            if (rand[0]*rand[0] + rand[1]*rand[1] <= 1){
                n++;
            }
        }
        double pi = ((double) n/(double) total*4);
        return pi;
    }

    @GetMapping("/test3")
    public String concat(){
        String [] a = {"f","o","o","bar"};
        int [] t = new int[7];
        String foobar = new String("");
        for (String f : a){
            foobar += f;
        }
        return foobar;
    }

    @GetMapping("/test4")
    public String echo(){
        String mot = "Hello Développeur java";
        String hello = "Hello";
        String java  = "java";
        if (Arrays.asList(mot.split(" ")).contains(java) || Arrays.asList(mot.split(" ")).contains(hello)) {
            System.out.println(hello);
            System.out.println();
            System.out.println(java);
        }
        return hello+ " : " +java;
    }

    @GetMapping("/test5")
    public int[] occurence(){
        int tab[] = {4,5,1,-2,4,10,6,8,5,6};
        int [] response = new int[10];
        for (int i = 0;i<tab.length;i++){
            for (int j = i+ 1; j< tab.length;j++){
                if (response[j] != tab[i]){

                    j++;
                }
            }
        }
        Arrays.sort(response);
        return response;
    }
}
