package com.pdict.iplpredict.entities;

public class LoginCredentials {
    public String userName;
    public String password;

    public LoginCredentials() {}

    public LoginCredentials(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginCredentials{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
