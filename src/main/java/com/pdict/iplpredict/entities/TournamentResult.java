package com.pdict.iplpredict.entities;

public class TournamentResult {
    public Integer tournamentYear;
    public String orangeCap;
    public String purpleCap;
    public String firstTeam;
    public String secondTeam;
    public String thirdTeam;
    public String fourthTeam;


    public TournamentResult(Integer tournamentYear, String orangeCap, String purpleCap, String firstTeam, String secondTeam, String thirdTeam, String fourthTeam) {
        this.tournamentYear = tournamentYear;
        this.orangeCap = orangeCap;
        this.purpleCap = purpleCap;
        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;
        this.thirdTeam = thirdTeam;
        this.fourthTeam = fourthTeam;
    }

    public TournamentResult(){}
}
