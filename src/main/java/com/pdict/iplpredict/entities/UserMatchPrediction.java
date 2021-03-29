package com.pdict.iplpredict.entities;

public class UserMatchPrediction {
    public String username;
    public String favTeam;
    public Integer matchId;
    public String teamWin;
    public Integer teamHigh1;
    public Integer teamLow1;
    public Integer teamHigh2;
    public Integer teamLow2;
    public Integer wickets;

    public UserMatchPrediction() {}

    public UserMatchPrediction(String username, String favTeam, Integer matchId, String teamWin, Integer teamHigh1, Integer teamLow1, Integer teamHigh2, Integer teamLow2, Integer wickets) {
        this.username = username;
        this.favTeam = favTeam;
        this.matchId = matchId;
        this.teamWin = teamWin;
        this.teamHigh1 = teamHigh1;
        this.teamLow1 = teamLow1;
        this.teamHigh2 = teamHigh2;
        this.teamLow2 = teamLow2;
        this.wickets = wickets;
    }

    @Override
    public String toString() {
        return "UserMatchPrediction{" + "username='" + username + '\'' + ", favTeam='" + favTeam + '\'' + ", matchId" +
                "=" + matchId + ", teamWin='" + teamWin + '\'' + ", teamHigh1=" + teamHigh1 + ", teamLow1=" + teamLow1 + ", teamHigh2=" + teamHigh2 + ", teamLow2=" + teamLow2 + ", wickets=" + wickets + '}';
    }
}
