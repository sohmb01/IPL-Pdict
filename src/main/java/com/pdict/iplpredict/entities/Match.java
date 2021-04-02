package com.pdict.iplpredict.entities;

import java.sql.Date;
import java.sql.Time;

public class Match {
    public String matchId;
    public Date matchDate;
    public Time matchStartTime;
    public Boolean isFinished;
    public String matchType;
    public String teamWin;
    public String teamId1;
    public String teamId2;
    public Integer teamScore1;
    public Integer teamScore2;
    public Integer wickets;

    public Match() {}

    public Match(String matchId, Date matchDate, Time matchStartTime, Boolean isFinished, String matchType, String teamWin, String teamId1, String teamId2, Integer teamScore1, Integer teamScore2, Integer wickets) {
        this.matchId = matchId;
        this.matchDate = matchDate;
        this.matchStartTime = matchStartTime;
        this.isFinished = isFinished;
        this.matchType = matchType;
        this.teamWin = teamWin;
        this.teamId1 = teamId1;
        this.teamId2 = teamId2;
        this.teamScore1 = teamScore1;
        this.teamScore2 = teamScore2;
        this.wickets = wickets;
    }

    @Override
    public String toString() {
        return "Match{" +
                "matchId='" + matchId + '\'' +
                ", matchDate=" + matchDate +
                ", matchStartTime=" + matchStartTime +
                ", isFinished=" + isFinished +
                ", matchType='" + matchType + '\'' +
                ", teamWin='" + teamWin + '\'' +
                ", teamId1='" + teamId1 + '\'' +
                ", teamId2='" + teamId2 + '\'' +
                ", teamScore1=" + teamScore1 +
                ", teamScore2=" + teamScore2 +
                ", wickets=" + wickets +
                '}';
    }
}