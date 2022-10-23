package com.example.demo.credentials;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String userName;
    private String passWord;

    public User() {
    }

    public User(String uname, String pwrd) {
        this.userName = uname;
        this.passWord = pwrd;
    }
 
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", uname='" + userName + '\'' +
                ", pwrd='" + passWord + '\'' +
                '}';
    }
}