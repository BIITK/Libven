package com.example.libven;

import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private String nick;
    private String password;
    private String phone;
    private String email;

    public User(String name, String nick, String password, String phone, String email) {
        this.name = name;
        this.nick = nick;
        this.password = password;
        this.phone = phone;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return  "\nname='" + name +
                "\nnick='" + nick +
                "\npassword='" + password +
                "\nphone='" + phone +
                "\nemail='" + email;
    }
}
