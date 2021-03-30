package com.pdict.iplpredict.entities;

public class UserPoints {
    public String username;
    public Integer matchPoints;

    public UserPoints(String username, Integer matchPoints) {
        this.username = username;
        this.matchPoints = matchPoints;
    }
     public UserPoints() {}
}
