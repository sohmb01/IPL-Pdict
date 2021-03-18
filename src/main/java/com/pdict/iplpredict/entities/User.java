package com.pdict.iplpredict.entities;

public class User {
    public Integer userId;
    public String username;
    public String password;
    public String fullName;
    public String favTeam;

    public User(Integer userId, String username, String password, String fullName, String favTeam) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.favTeam = favTeam;
    }
}
