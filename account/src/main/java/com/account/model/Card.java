package com.account.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "card")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCard;
    private String numberCard;
    private String typeCard;
    private String name;
    private Date expiration;
    private Integer cvc;

    public Card() {
    }

    public Card(String numberCard, String typeCard, String name, Date expiration, Integer cvc) {
        this.numberCard = numberCard;
        this.typeCard = typeCard;
        this.name = name;
        this.expiration = expiration;
        this.cvc = cvc;
    }

    public long getIdCard() {
        return idCard;
    }

    public void setIdCard(long idCard) {
        this.idCard = idCard;
    }

    public String getNumberCard() {
        return numberCard;
    }

    public void setNumberCard(String numberCard) {
        this.numberCard = numberCard;
    }

    public String getTypeCard() {
        return typeCard;
    }

    public void setTypeCard(String typeCard) {
        this.typeCard = typeCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public Integer getCvc() {
        return cvc;
    }

    public void setCvc(Integer cvc) {
        this.cvc = cvc;
    }
}
