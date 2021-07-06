package com.account.model;

import com.account.beans.UserBean;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bank")
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idBank;
    // longueur 5 numbers
    private Integer bankCode;
    // longueur 5 numbers
    private Integer branchCode;
    private String address;

    @OneToMany(targetEntity = UserBean.class, mappedBy = "bank")
    private List<UserBean> users = new ArrayList<>();

    public Bank() {
    }

    public Bank(Integer bankCode,Integer branchCode, String address) {
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

    public List<UserBean> getUsers() {
        return users;
    }

}
