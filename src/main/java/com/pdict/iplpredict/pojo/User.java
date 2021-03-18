package com.pdict.iplpredict.pojo;

public class User {
    private Integer user_id;
    private String full_name;
    private String fav_team;

    public User(Integer user_id, String full_name, String fav_team) {
        this.user_id = user_id;
        this.full_name = full_name;
        this.fav_team = fav_team;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getFav_team() {
        return fav_team;
    }

    public void setFav_team(String fav_team) {
        this.fav_team = fav_team;
    }
}
