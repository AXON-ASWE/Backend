package com.swe.project.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "T_USER")
public class Users {
    @Id
    @GeneratedValue()
    private Integer id;
    private String username;
    private String password;
    public Users(){

    }
    public Users(String username, String password){
        this.username = username;
        this.password = password;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
