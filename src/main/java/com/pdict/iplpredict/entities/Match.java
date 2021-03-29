package com.pdict.iplpredict.entities;

public class Match {
    public Integer matchId;
    public Integer tournamentYear;
    public String teamWin;
    public String teamId1;
    public String teamId2;
    public Integer teamScore1;
    public Integer teamScore2;
    public Integer wickets;

    public Match() {}
    
    public Match(Integer matchId, Integer tournamentYear, String teamWin, String teamId1, String teamId2, Integer teamScore1, Integer teamScore2, Integer wickets) {
        this.matchId = matchId;
        this.tournamentYear = tournamentYear;
        this.teamWin = teamWin;
        this.teamId1 = teamId1;
        this.teamId2 = teamId2;
        this.teamScore1 = teamScore1;
        this.teamScore2 = teamScore2;
        this.wickets = wickets;
    }

    @Override
    public String toString() {
        return "Match{" + "matchId=" + matchId + ", tournamentYear=" + tournamentYear + ", teamWin='" + teamWin + '\'' + ", teamId1='" + teamId1 + '\'' + ", teamId2='" + teamId2 + '\'' + ", teamScore1=" + teamScore1 + ", teamScore2=" + teamScore2 + ", wickets=" + wickets + '}';
    }
}