package com.pdict.iplpredict.entities;

import java.util.List;

public class Tournament {
    public Integer tournamentYear;
    public String winningTeam;
    public String runnerUpTeam;
    public List<String> semiFinalists;
    public String orangeCap;
    public String purpleCap;

    public Tournament(){}

    public Tournament(Integer tournamentYear, String winningTeam, String runnerUpTeam, List<String> semiFinalists,
                      String orangeCap, String purpleCap) {
        this.tournamentYear = tournamentYear;
        this.winningTeam = winningTeam;
        this.runnerUpTeam = runnerUpTeam;
        this.semiFinalists = semiFinalists;
        this.orangeCap = orangeCap;
        this.purpleCap = purpleCap;
    }

    @Override
    public String toString() {
        return "Tournament{" + "tournamentYear=" + tournamentYear + ", winningTeam='" + winningTeam + '\'' + ", " +
                "runnerUpTeam='" + runnerUpTeam + '\'' + ", semiFinalists=" + semiFinalists + ", orangeCap='" + orangeCap + '\'' + ", purpleCap='" + purpleCap + '\'' + '}';
    }
}
