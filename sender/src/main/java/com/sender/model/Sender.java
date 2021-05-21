package com.sender.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sender.bean.UserDTO;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sender")
public class Sender {

    private Integer idSender;
    private String firstname;
    private String lastname;
    private String email;
    private Integer idUser;

    @ManyToMany(mappedBy = "senders",targetEntity = UserDTO.class)
    @JsonIgnoreProperties("senders")
    private List<UserDTO> users = new ArrayList<>();

    public Sender() {
    }

    public Sender(String firstname, String lastname, String email, Integer idUser) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.idUser = idUser;
    }

    public Integer getIdSender() {
        return idSender;
    }

    public void setIdSender(Integer idSender) {
        this.idSender = idSender;
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

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }
}
