package com.pdict.iplpredict.entities;

public class MatchwisePrediction {
    public String username;
    public Integer matchId;
    public String teamWin;
    public Integer teamHigh1;
    public Integer teamLow1;
    public Integer teamHigh2;
    public Integer teamLow2;
    public Integer wickets;

    public MatchwisePrediction(String username, Integer matchId, String teamWin, Integer teamHigh1, Integer teamLow1, Integer teamHigh2, Integer teamLow2, Integer wickets) {
        this.username = username;
        this.matchId = matchId;
        this.teamWin = teamWin;
        this.teamHigh1 = teamHigh1;
        this.teamLow1 = teamLow1;
        this.teamHigh2 = teamHigh2;
        this.teamLow2 = teamLow2;
        this.wickets = wickets;
    }
}
