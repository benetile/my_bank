package com.user.beans;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "account")
public class AccountBean {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAccount;
    private long accountNumber;
    private String iban;
    private String bic;
    private String firstname;
    private String lastname;
    private BigDecimal sold;
    private BigDecimal ceiling;
    //private Integer idUser;
    private BigDecimal discoveredMax;
    private BigDecimal discovered;

    public AccountBean() {
    }

    public Integer getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(Integer idAccount) {
        this.idAccount = idAccount;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public BigDecimal getSold() {
        return sold;
    }

    public void setSold(BigDecimal sold) {
        this.sold = sold;
    }

    public BigDecimal getCeiling() {
        return ceiling;
    }

    public void setCeiling(BigDecimal ceiling) {
        this.ceiling = ceiling;
    }

    public BigDecimal getDiscoveredMax() {
        return discoveredMax;
    }

    public void setDiscoveredMax(BigDecimal discoveredMax) {
        this.discoveredMax = discoveredMax;
    }

    public BigDecimal getDiscovered() {
        return discovered;
    }

    public void setDiscovered(BigDecimal discovered) {
        this.discovered = discovered;
    }
}
