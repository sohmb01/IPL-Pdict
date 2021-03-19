package com.pdict.iplpredict.database;

import com.pdict.iplpredict.entities.Prediction;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PredictionRepository  {
    DatabaseInteraction databaseInteraction = new DatabaseInteraction();

    public Prediction getPredictionByUserName(String userName) throws SQLException {
        String sql = "SELECT * FROM \"prediction\" WHERE userName='" + userName + "';";

        ResultSet resultSet = databaseInteraction.executeQuery(sql);
        resultSet.next();


        Integer tournamentYear = resultSet.getInt("tournament_year");
        String orangeCap1 = resultSet.getString("orange_cap1");
        String orangeCap2 = resultSet.getString("orange_cap2");
        String orangeCap3 = resultSet.getString("orange_cap3");
        String purpleCap1 = resultSet.getString("purple_cap1");
        String purpleCap2 = resultSet.getString("purple_cap2");
        String purpleCap3 = resultSet.getString("purple_cap3");
        String firstTeam  = resultSet.getString("sf1");
        String secondTeam = resultSet.getString("sf2");
        String thirdTeam  = resultSet.getString("sf3");
        String fourthTeam = resultSet.getString("sf4");



        return new Prediction( userName , tournamentYear , orangeCap1 , orangeCap2 , orangeCap3 ,purpleCap1 , purpleCap2 , purpleCap3 , firstTeam , secondTeam , thirdTeam , fourthTeam);
    }

    public void addPrediction(String userName, Integer tournamentYear, String orangeCap1, String orangeCap2, String orangeCap3, String purpleCap1, String purpleCap2, String purpleCap3, String first_Team, String second_Team, String third_Team, String fourth_Team) throws SQLException {
        String sql = "INSERT INTO \"prediction\" (userName, tournament_year, orange_cap1, orange_cap2, orange_cap3, purple_cap1, purple_cap2, purple_cap3, sf1, sf2, sf3) VALUES ('"++"', '"+password+"', '"+fullName+"', '"+favTeam+"');";

        databaseInteraction.executeUpdate(sql);
    }

}
