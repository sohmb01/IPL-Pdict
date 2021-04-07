package com.pdict.iplpredict.entities;

public class MatchPrediction {
    public String userName;
    public String matchId;
    public String teamWin;
    public Integer teamHigh1;
    public Integer teamLow1;
    public Integer teamHigh2;
    public Integer teamLow2;
    public Integer wickets;

    public MatchPrediction() {}

    public MatchPrediction(String userName, String matchId, String teamWin, Integer teamHigh1, Integer teamLow1, Integer teamHigh2, Integer teamLow2, Integer wickets) {
        this.userName = userName;
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
        return "MatchPrediction{" + "username='" + userName + '\'' + ", matchId=" + matchId + ", teamWin='" + teamWin + '\'' + ", teamHigh1=" + teamHigh1 + ", teamLow1=" + teamLow1 + ", teamHigh2=" + teamHigh2 + ", teamLow2=" + teamLow2 + ", wickets=" + wickets + '}';
    }
}
