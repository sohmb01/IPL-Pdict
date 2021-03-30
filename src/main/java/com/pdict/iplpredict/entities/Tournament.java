package com.pdict.iplpredict.entities;

import java.sql.Date;
import java.util.List;

public class Tournament {
    public Integer tournamentYear;
    public Date tournamentStartDate;
    public Date tournamentEndDate;
    public Boolean isFinished;
    public String winningTeam;
    public String runnerUpTeam;
    public List<String> semiFinalists;
    public String orangeCap;
    public String purpleCap;

    public Tournament(){}


    public Tournament(Integer tournamentYear, Date tournamentStartDate, Date tournamentEndDate, Boolean isFinished, String winningTeam, String runnerUpTeam, List<String> semiFinalists, String orangeCap, String purpleCap) {
        this.tournamentYear = tournamentYear;
        this.tournamentStartDate = tournamentStartDate;
        this.tournamentEndDate = tournamentEndDate;
        this.isFinished = isFinished;
        this.winningTeam = winningTeam;
        this.runnerUpTeam = runnerUpTeam;
        this.semiFinalists = semiFinalists;
        this.orangeCap = orangeCap;
        this.purpleCap = purpleCap;
    }

    @Override
    public String toString() {
        return "Tournament{" +
                "tournamentYear=" + tournamentYear +
                ", tournamentStartDate=" + tournamentStartDate +
                ", tournamentEndDate=" + tournamentEndDate +
                ", isFinished=" + isFinished +
                ", winningTeam='" + winningTeam + '\'' +
                ", runnerUpTeam='" + runnerUpTeam + '\'' +
                ", semiFinalists=" + semiFinalists +
                ", orangeCap='" + orangeCap + '\'' +
                ", purpleCap='" + purpleCap + '\'' +
                '}';
    }
}
