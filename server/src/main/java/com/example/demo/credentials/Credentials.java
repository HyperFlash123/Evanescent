package com.example.demo.credentials;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Credentials {

    @Id
    @GeneratedValue
    private Long id;
    private String userName;
    private String passWord;
    private String webSite;
    private String activeStatus;
    private String actionLink;

    public Credentials() {
    }

    public Credentials(String uname, String pwrd, String wSite, String aStatus, String aLink) {
        this.userName = uname;
        this.passWord = pwrd;
        this.webSite = wSite;
        this.activeStatus = aStatus;
        this.actionLink = aLink;
    }
 
    @Override
    public String toString() {
        return "Credentials{" +
                "id=" + id +
                ", uname='" + userName + '\'' +
                ", pwrd='" + passWord + '\'' +
                ", wSite='" + webSite + '\'' +
                ", aStatus='" + activeStatus + '\'' +
                ", aLink='" + actionLink + '\'' +
                '}';
    }
}