package com.pdict.iplpredict.entities;

import java.util.List;

public class TournamentPrediction {
    public String userName;
    public Integer tournamentYear;
    public String winningTeam;
    public List<String> semiFinalists;
    public List<String> orangeCaps;
    public List<String> purpleCaps;

    public TournamentPrediction() {}

    public TournamentPrediction(String userName, Integer tournamentYear, String winningTeam,
                                List<String> semiFinalists, List<String> orangeCaps, List<String> purpleCaps) {
        this.userName = userName;
        this.tournamentYear = tournamentYear;
        this.winningTeam = winningTeam;
        this.semiFinalists = semiFinalists;
        this.orangeCaps = orangeCaps;
        this.purpleCaps = purpleCaps;
    }
}