package com.user.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.user.model.User;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "beneficiary")
public class BeneficiaryBean {

    @Id
    private Integer idBeneficiary;
    private String firstname;
    private String lastname;
    private String email;
    private String iban;
    private String phone;

    @ManyToMany(mappedBy = "beneficiaries",targetEntity = User.class)
    @JsonIgnoreProperties("beneficiaries")
    private List<User> users = new ArrayList<>();

    public BeneficiaryBean() {
    }

    public BeneficiaryBean(String firstname, String lastname, String email, String iban, String phone) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.iban = iban;
        this.phone = phone;
    }

    public Integer getIdBeneficiary() {
        return idBeneficiary;
    }

    public void setIdBeneficiary(Integer idBeneficiary) {
        this.idBeneficiary = idBeneficiary;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
