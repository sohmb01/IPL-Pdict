package com.pdict.iplpredict.entities;

public class User {
    public String userName;
    public String password;
    public String fullName;
    public String favTeam;

    public User(String userName, String password, String fullName, String favTeam) {
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.favTeam = favTeam;
    }
}
