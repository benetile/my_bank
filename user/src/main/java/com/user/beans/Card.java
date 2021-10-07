package com.user.beans;

import java.math.BigDecimal;
import java.util.Date;

public class Card {

    private long idCard;
    private long numberOfCard;
    private String typeCard;
    private String name;
    private Integer cvv;
    private BigDecimal sold;
    private Date expiration;

    public Card() {
    }

    public Card(long numberOfCard, String typeCard, String name, Integer cvv, BigDecimal sold, Date expiration) {
        this.numberOfCard = numberOfCard;
        this.typeCard = typeCard;
        this.name = name;
        this.cvv = cvv;
        this.sold = sold;
        this.expiration = expiration;
    }

    public long getIdCard() {
        return idCard;
    }

    public void setIdCard(long idCard) {
        this.idCard = idCard;
    }

    public long getNumberOfCard() {
        return numberOfCard;
    }

    public void setNumberOfCard(long numberOfCard) {
        this.numberOfCard = numberOfCard;
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

    public Integer getCvv() {
        return cvv;
    }

    public void setCvv(Integer cvv) {
        this.cvv = cvv;
    }

    public BigDecimal getSold() {
        return sold;
    }

    public void setSold(BigDecimal sold) {
        this.sold = sold;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }
}
