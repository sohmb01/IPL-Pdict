package com.pdict.iplpredict.entities;

public class Prediction {
    public String userName;
    public Integer tournamentYear;
    public String orangeCap1;
    public String orangeCap2;
    public String orangeCap3;
    public String purpleCap1;
    public String purpleCap2;
    public String purpleCap3;
    public String firstTeam;
    public String secondTeam;
    public String thirdTeam;
    public String fourthTeam;

    public Prediction(){}

    public Prediction(String userName, Integer tournamentYear, String orangeCap1, String orangeCap2, String orangeCap3, String purpleCap1, String purpleCap2, String purpleCap3, String first_Team, String second_Team, String third_Team, String fourth_Team) {
        this.userName = userName;
        this.tournamentYear = tournamentYear;
        this.orangeCap1 = orangeCap1;
        this.orangeCap2 = orangeCap2;
        this.orangeCap3 = orangeCap3;
        this.purpleCap1 = purpleCap1;
        this.purpleCap2 = purpleCap2;
        this.purpleCap3 = purpleCap3;
        this.firstTeam = first_Team;
        this.secondTeam = second_Team;
        this.thirdTeam = third_Team;
        this.fourthTeam = fourth_Team;
    }
}