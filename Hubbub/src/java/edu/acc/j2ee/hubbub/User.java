package edu.acc.j2ee.hubbub;

import java.util.Date;

public class User implements java.io.Serializable {
    private String username;
    private String password;
    private Date joinDate;

    public User() {
    }
    
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.joinDate = new Date();
    }

    public User(String username, String password, Date joinDate) {
        this.username = username;
        this.password = password;
        this.joinDate = joinDate;
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

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return username;
    }

}