package com.quidditchreftraining.qreftrain.dto;

public class userDTO {
    int id;
    String mail;
    String password;
    String displayName;
    String token;

    public userDTO(int id, String mail, String password, String displayName, String token) {
        this.id = id;
        this.mail = mail;
        this.password = password;
        this.displayName = displayName;
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
