package com.pdict.iplpredict.entities;

import java.sql.Date;
import java.sql.Time;

public class Match {
    public String matchId;
    public Integer matchStartMinute;
    public Integer matchStartHour;
    public Integer matchStartDay;
    public Integer matchStartMonth;
    public Integer matchStartYear;
    public Boolean isFinished;
    public String matchType;
    public String teamWin;
    public String teamId1;
    public String teamId2;
    public Integer teamScore1;
    public Integer teamScore2;
    public Integer wickets;

    public Match() {}

    public Match(String matchId, Integer matchStartMinute, Integer matchStartHour, Integer matchStartDay, Integer matchStartMonth, Integer matchStartYear, Boolean isFinished, String matchType, String teamWin, String teamId1, String teamId2, Integer teamScore1, Integer teamScore2, Integer wickets) {
        this.matchId = matchId;
        this.matchStartMinute = matchStartMinute;
        this.matchStartHour = matchStartHour;
        this.matchStartDay = matchStartDay;
        this.matchStartMonth = matchStartMonth;
        this.matchStartYear = matchStartYear;
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
                ", matchStartMinute=" + matchStartMinute +
                ", matchStartHour=" + matchStartHour +
                ", matchStartDay=" + matchStartDay +
                ", matchStartMonth=" + matchStartMonth +
                ", matchStartYear=" + matchStartYear +
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