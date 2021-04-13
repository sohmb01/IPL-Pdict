package com.pdict.iplpredict.entities;

public class SuperOver {
    public Integer superOverNumber;
    public String matchId;
    public Integer superOverStartMinute;
    public Integer superOverStartHour;
    public Integer superOverStartDay;
    public Integer superOverStartMonth;
    public Integer superOverStartYear;
    public Boolean isFinished;
    public String teamWin;
    public String teamId1;
    public String teamId2;
    public Integer teamScore1;
    public Integer teamScore2;
    public Integer wickets;

    public SuperOver() {}

    public SuperOver(Integer superOverNumber, String matchId, Integer superOverStartMinute,
                     Integer superOverStartHour, Integer superOverStartDay, Integer superOverStartMonth,
                     Integer superOverStartYear, Boolean isFinished, String teamWin, String teamId1, String teamId2,
                     Integer teamScore1, Integer teamScore2, Integer wickets) {
        this.superOverNumber = superOverNumber;
        this.matchId = matchId;
        this.superOverStartMinute = superOverStartMinute;
        this.superOverStartHour = superOverStartHour;
        this.superOverStartDay = superOverStartDay;
        this.superOverStartMonth = superOverStartMonth;
        this.superOverStartYear = superOverStartYear;
        this.isFinished = isFinished;
        this.teamWin = teamWin;
        this.teamId1 = teamId1;
        this.teamId2 = teamId2;
        this.teamScore1 = teamScore1;
        this.teamScore2 = teamScore2;
        this.wickets = wickets;
    }

    @Override
    public String toString() {
        return "SuperOver{" + "superOverNumber=" + superOverNumber + ", matchId='" + matchId + '\'' + ", " +
                "superOverStartMinute=" + superOverStartMinute + ", superOverStartHour=" + superOverStartHour + ", " +
                "superOverStartDay=" + superOverStartDay + ", superOverStartMonth=" + superOverStartMonth + ", " +
                "superOverStartYear=" + superOverStartYear + ", isFinished=" + isFinished + ", teamWin='" + teamWin + '\'' + ", teamId1='" + teamId1 + '\'' + ", teamId2='" + teamId2 + '\'' + ", teamScore1=" + teamScore1 + ", teamScore2=" + teamScore2 + ", wickets=" + wickets + '}';
    }
}
