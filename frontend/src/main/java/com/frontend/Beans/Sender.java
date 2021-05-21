package com.frontend.Beans;

import java.util.ArrayList;
import java.util.List;

public class Sender {

    private Integer idSenderBean;
    private String firstname;
    private String lastname;
    private String email;
    private Integer idUser;

    private List<User> users = new ArrayList<>();

    public Sender(String firstname, String lastname, String email, Integer idUser) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.idUser = idUser;
    }

    public Integer getIdSenderBean() {
        return idSenderBean;
    }

    public void setIdSenderBean(Integer idSenderBean) {
        this.idSenderBean = idSenderBean;
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
