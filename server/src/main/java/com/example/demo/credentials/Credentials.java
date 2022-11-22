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
    private Long id;
    private String user;
    private String userName;
    private String cpwd;
    private String passWord;
    private String webSite;
    private String activeStatus;
    private String actionLink;

    public Credentials() {

    }
    
    public Credentials(int i, String user, String uname, String pwrd, String cpwd, String wSite, String aStatus, String aLink) {
        this.id = (long) i;
        this.userName = uname;
        this.passWord = pwrd;
        this.webSite = wSite;
        this.activeStatus = aStatus;
        this.actionLink = aLink;
        this.user = user;
        this.cpwd = cpwd;
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
                ", user='" + user + '\'' +
                ", cpwd='" + cpwd + '\'' +
                '}';
    }
}