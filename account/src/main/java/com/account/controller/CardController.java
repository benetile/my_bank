package com.account.controller;

import com.account.model.Card;
import com.account.model.CardType;
import com.account.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CardController {

    @Autowired
    private CardRepository cardRepository;

    //public CardType cardType;

    @GetMapping("/cards")
    public List<Card> showAllCards(){
        return cardRepository.findAll();
    }

    @GetMapping("/cards/{id}")
    public Optional<Card> getCardById(@PathVariable("id") long id){
        if (cardRepository.existsById(id)){
            return cardRepository.findById(id);
        }
        throw new IllegalArgumentException("Invalid Id for card : "+id);
    }

    @GetMapping("/cards/number/{number}")
    public Card getCardByNumberCard(@PathVariable("number") long number){
        if (cardRepository.findByNumberCard(number)!= null){
            return cardRepository.findByNumberCard(number);
        }
        throw new IllegalArgumentException("Invalid number of card doesn't exist : "+number);
    }

    @PostMapping("/cards")
    public Card addNewCard(@RequestBody Card card,BindingResult bindingResult){
        if (!bindingResult.hasErrors()){
            return cardRepository.save(card);
        }
        throw new NullPointerException("Error cannot ");
    }

    @PutMapping("/cards/{id}")
    public Card updateCard(@PathVariable("id") long id,@RequestBody Card update){
        if (cardRepository.existsById(id)){
           // Card card = cardRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid Id : "+id));
            update.setIdCard(id);
            return cardRepository.save(update);
        }
        throw new IllegalArgumentException("Invalid Id : "+id);
    }

    @DeleteMapping("/cards/{id}")
    public void deleteCard(@PathVariable("id") long id){
        if (cardRepository.existsById(id)){
            cardRepository.deleteById(id);
        }
        throw new IllegalArgumentException("Invalid Id : "+id);
    }
}
