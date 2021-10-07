package com.user.model;

import java.io.Serializable;

public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private String token;
    private String type ="Bearer ";
    private Long id;

    public JwtResponse(String token, String type) {
        this.token = token;
        this.type = type;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getToken() {
        return this.token;
    }
}
