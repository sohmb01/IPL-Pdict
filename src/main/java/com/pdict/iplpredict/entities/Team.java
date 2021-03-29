package com.pdict.iplpredict.entities;

public class Team {
    public String teamCode;
    public String teamName;

    public Team() {}

    public Team(String teamCode, String teamName) {
        this.teamCode = teamCode;
        this.teamName = teamName;
    }

    @Override
    public String toString() {
        return "Team{" + "teamCode='" + teamCode + '\'' + ", teamName='" + teamName + '\'' + '}';
    }
}
