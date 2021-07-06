package com.user.beans;

import com.user.model.User;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bank")
public class BankBean {

    @Id
    private Integer idBank;
    // longueur 5 numbers
    private Integer bankCode;
    // longueur 5 numbers
    private Integer branchCode;
    private String address;

   /* @OneToMany
    @JoinTable(name = "user_bank",
            joinColumns = @JoinColumn(name = "idBank"),
            inverseJoinColumns = @JoinColumn(name = "idUser"))
    private List<User> users = new ArrayList<>();*/
   @OneToMany(targetEntity = User.class, mappedBy = "bank")
   private List<User> users = new ArrayList<>();

    public BankBean() {
    }

    public BankBean(Integer bankCode, Integer branchCode, String address) {
        super();
        this.bankCode = bankCode;
        this.branchCode = branchCode;
        this.address = address;
    }

    public Integer getIdBank() {
        return idBank;
    }

    public void setIdBank(Integer idBank) {
        this.idBank = idBank;
    }

    public Integer getBankCode() {
        return bankCode;
    }

    public void setBankCode(Integer bankCode) {
        this.bankCode = bankCode;
    }

    public Integer getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(Integer branchCode) {
        this.branchCode = branchCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<User> getUsers() {
        return users;
    }

}
