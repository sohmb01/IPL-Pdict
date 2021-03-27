package com.pdict.iplpredict.entities;

import java.util.List;

public class UserTournamentPrediction {
    public String userName;
    public String favTeam;
    public Integer tournamentYear;
    public String winningTeam;
    public List<String> semiFinalists;
    public List<String> orangeCaps;
    public List<String> purpleCaps;

    public UserTournamentPrediction() {}

    public UserTournamentPrediction(String userName, String favTeam, Integer tournamentYear, String winningTeam,
                                    List<String> semiFinalists, List<String> orangeCaps, List<String> purpleCaps) {
        this.userName = userName;
        this.favTeam = favTeam;
        this.tournamentYear = tournamentYear;
        this.winningTeam = winningTeam;
        this.semiFinalists = semiFinalists;
        this.orangeCaps = orangeCaps;
        this.purpleCaps = purpleCaps;
    }
}
