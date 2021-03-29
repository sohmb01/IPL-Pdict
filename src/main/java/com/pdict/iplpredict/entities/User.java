package com.pdict.iplpredict.entities;

public class User {
    public String userName;
    public String password;
    public String fullName;
    public String favTeam;

    public User() {}

    public User(String userName, String password, String fullName, String favTeam) {
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.favTeam = favTeam;
    }

    @Override
    public String toString() {
        return "User{" + "userName='" + userName + '\'' + ", password='" + password + '\'' + ", fullName='" + fullName + '\'' + ", favTeam='" + favTeam + '\'' + '}';
    }
}
