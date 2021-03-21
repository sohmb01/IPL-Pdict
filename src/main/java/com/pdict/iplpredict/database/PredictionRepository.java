package com.pdict.iplpredict.database;

import com.pdict.iplpredict.entities.Prediction;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PredictionRepository  {
    DatabaseInteraction databaseInteraction = new DatabaseInteraction();

    public Prediction getPrediction(String userName , Integer tournamentYear) throws SQLException {
        String sql = "SELECT * FROM \"prediction\" WHERE username='" + userName + "' and tournament_year='"+ tournamentYear +"';" ;

        ResultSet resultSet = databaseInteraction.executeQuery(sql);
        resultSet.next();


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

    public void addPrediction(Prediction prediction) throws SQLException {
        String sql = "INSERT INTO \"prediction\" (username, tournament_year, orange_cap1, orange_cap2, orange_cap3, purple_cap1, purple_cap2, purple_cap3, sf1, sf2, sf3, sf4) VALUES ('"+prediction.userName+"', '"+prediction.tournamentYear+"', '"+prediction.orangeCap1+"', '"+prediction.orangeCap2+"', '"+prediction.orangeCap3+"', '"+prediction.purpleCap1+"', '"+prediction.purpleCap2+"', '"+prediction.purpleCap3+"', '"+prediction.firstTeam+"', '"+prediction.secondTeam+"', '"+prediction.thirdTeam+"', '"+prediction.fourthTeam+"');";

        databaseInteraction.executeUpdate(sql);
    }

    public void updatePrediction(Prediction prediction) throws SQLException{
        String sql = "UPDATE \"prediction\" SET orange_cap1='"+prediction.orangeCap1+"', orange_cap2='"+prediction.orangeCap2+"', orange_cap3='"+ prediction.orangeCap3+"', purple_cap1='"+ prediction.purpleCap1+"', purple_cap2='"+ prediction.purpleCap2+"', purple_cap3='"+ prediction.purpleCap3+"', sf1='"+ prediction.firstTeam+"', sf2='"+ prediction.secondTeam+"', sf3='"+ prediction.thirdTeam+"', sf4='"+ prediction.fourthTeam+"' WHERE username='"+prediction.userName+"' and tournament_year='"+ prediction.tournamentYear+"' ;";

        databaseInteraction.executeUpdate(sql);
    }

    public void deletePrediction(String userName , Integer tournamentYear) throws  SQLException{
        String sql = "DELETE FROM \"prediction\" WHERE username='"+userName+"' and tournament_year='"+tournamentYear+"' ;";

        databaseInteraction.executeUpdate(sql);
    }
}