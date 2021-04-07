package com.pdict.iplpredict.entities;

import java.sql.Date;
import java.util.List;

public class Tournament {
    public Integer tournamentYear;
    public Integer tournamentStartDay;
    public Integer tournamentStartMonth;
    public Integer tournamentStartYear;
    public Integer tournamentEndDay;
    public Integer tournamentEndMonth;
    public Integer tournamentEndYear;
    public Boolean isFinished;
    public String winningTeam;
    public String runnerUpTeam;
    public List<String> semiFinalists;
    public String orangeCap;
    public String purpleCap;

    public Tournament(){}

    public Tournament(Integer tournamentYear, Integer tournamentStartDay, Integer tournamentStartMonth, Integer tournamentStartYear, Integer tournamentEndDay, Integer tournamentEndMonth, Integer tournamentEndYear, Boolean isFinished, String winningTeam, String runnerUpTeam, List<String> semiFinalists, String orangeCap, String purpleCap) {
        this.tournamentYear = tournamentYear;
        this.tournamentStartDay = tournamentStartDay;
        this.tournamentStartMonth = tournamentStartMonth;
        this.tournamentStartYear = tournamentStartYear;
        this.tournamentEndDay = tournamentEndDay;
        this.tournamentEndMonth = tournamentEndMonth;
        this.tournamentEndYear = tournamentEndYear;
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
                ", tournamentStartDay=" + tournamentStartDay +
                ", tournamentStartMonth=" + tournamentStartMonth +
                ", tournamentStartYear=" + tournamentStartYear +
                ", tournamentEndDay=" + tournamentEndDay +
                ", tournamentEndMonth=" + tournamentEndMonth +
                ", tournamentEndYear=" + tournamentEndYear +
                ", isFinished=" + isFinished +
                ", winningTeam='" + winningTeam + '\'' +
                ", runnerUpTeam='" + runnerUpTeam + '\'' +
                ", semiFinalists=" + semiFinalists +
                ", orangeCap='" + orangeCap + '\'' +
                ", purpleCap='" + purpleCap + '\'' +
                '}';
    }
}
