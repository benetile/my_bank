package com.frontend.Beans;

import java.util.ArrayList;
import java.util.List;

public class Bank {

    private Integer idBank;
    // longueur 5 numbers
    private Integer bankCode;
    // longueur 5 numbers
    private Integer branchCode;
    private String address;

    private List<User>users = new ArrayList<>();

    public Bank(Integer bankCode, Integer branchCode, String address) {
        this.bankCode = bankCode;
        this.branchCode = branchCode;
        this.address = address;
    }

    public Bank() {
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

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
