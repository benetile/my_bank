package com.beneficiary.model;

import com.beneficiary.beans.UserDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.catalina.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "beneficiary")
public class Beneficiary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idBeneficiary;
    private String firstname;
    private String lastname;
    private String email;
    private String iban;
    private String phone;

    @JsonIgnoreProperties("beneficiaries")
    @ManyToMany(mappedBy = "beneficiaries",targetEntity = UserDTO.class)
    private List<UserDTO> users = new ArrayList<>();

    public Beneficiary() {
    }

    public Beneficiary(String firstname, String lastname, String email, String iban, String phone) {
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

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserDTO> users) {
        this.users = users;
    }
}
