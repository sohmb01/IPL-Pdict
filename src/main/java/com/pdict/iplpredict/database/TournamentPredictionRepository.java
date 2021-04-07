package com.pdict.iplpredict.database;

import com.pdict.iplpredict.entities.TournamentPrediction;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

public class TournamentPredictionRepository {

    public TournamentPrediction getTournamentPrediction(String userName , Integer tournamentYear) throws SQLException {
        String sql = "SELECT * FROM \"tournament_prediction\" WHERE username='" + userName + "' and tournament_year="+ tournamentYear +";" ;

        Connection conn = ConenctionPool.getConnection();
        Statement statement = conn.createStatement();

        try {
            ResultSet resultSet = statement.executeQuery(sql);

            if(resultSet.next()) {
                String winningTeam = resultSet.getString("winning_team");
                List<String> semiFinalists = Arrays.asList((String[]) resultSet.getArray("semi_finalists").getArray());
                List<String> orangeCaps = Arrays.asList((String[]) resultSet.getArray("orange_caps").getArray());
                List<String> purpleCaps = Arrays.asList((String[]) resultSet.getArray("purple_caps").getArray());

                return new TournamentPrediction(userName, tournamentYear, winningTeam, semiFinalists, orangeCaps, purpleCaps);
            } else {
                return null;
            }
        } finally {
            conn.close();
        }


    }

    public void addTournamentPrediction(TournamentPrediction tournamentPrediction) throws SQLException {
        String semiFinalistsArraySQL = "'{\""+ tournamentPrediction.semiFinalists.get(0)+"\",\""+ tournamentPrediction.semiFinalists.get(1)+"\",\""+ tournamentPrediction.semiFinalists.get(2)+"\",\""+ tournamentPrediction.semiFinalists.get(3)+"\"}'";
        String orangeCapsArraySQL = "'{\""+ tournamentPrediction.orangeCaps.get(0)+"\",\""+ tournamentPrediction.orangeCaps.get(1)+"\",\""+ tournamentPrediction.orangeCaps.get(2)+"\"}'";
        String purpleCapsArraySQL = "'{\""+ tournamentPrediction.purpleCaps.get(0)+"\",\""+ tournamentPrediction.purpleCaps.get(1)+"\",\""+ tournamentPrediction.purpleCaps.get(2)+"\"}'";

        String sql =
                "INSERT INTO \"tournament_prediction\" (username, tournament_year, winning_team, semi_finalists, orange_caps, purple_caps) VALUES ('"+ tournamentPrediction.userName+"', '"+ tournamentPrediction.tournamentYear+"', '"+ tournamentPrediction.winningTeam+"', "+semiFinalistsArraySQL+", "+orangeCapsArraySQL+", "+purpleCapsArraySQL+");";

        Connection conn = ConenctionPool.getConnection();
        Statement statement = conn.createStatement();

        try {
            statement.executeUpdate(sql);
        } finally {
            conn.close();
        }
    }

    public void updateTournamentPrediction(TournamentPrediction tournamentPrediction) throws SQLException{
        String semiFinalistsArraySQL = "'{\""+ tournamentPrediction.semiFinalists.get(0)+"\",\""+ tournamentPrediction.semiFinalists.get(1)+"\",\""+ tournamentPrediction.semiFinalists.get(2)+"\",\""+ tournamentPrediction.semiFinalists.get(3)+"\"}'";
        String orangeCapsArraySQL = "'{\""+ tournamentPrediction.orangeCaps.get(0)+"\",\""+ tournamentPrediction.orangeCaps.get(1)+"\",\""+ tournamentPrediction.orangeCaps.get(2)+"\"}'";
        String purpleCapsArraySQL = "'{\""+ tournamentPrediction.purpleCaps.get(0)+"\",\""+ tournamentPrediction.purpleCaps.get(1)+"\",\""+ tournamentPrediction.purpleCaps.get(2)+"\"}'";

        String sql = "UPDATE \"tournament_prediction\" SET winning_team='"+ tournamentPrediction.winningTeam+"', semi_finalists="+semiFinalistsArraySQL+", orange_caps="+orangeCapsArraySQL+", purple_caps="+purpleCapsArraySQL+" WHERE username='"+tournamentPrediction.userName+"' AND tournament_year="+tournamentPrediction.tournamentYear+";";

        Connection conn = ConenctionPool.getConnection();
        Statement statement = conn.createStatement();

        try {
            statement.executeUpdate(sql);
        } finally {
            conn.close();
        }
    }

//    public void deleteTournamentPrediction(String userName , Integer tournamentYear) throws  SQLException{
//        String sql = "DELETE FROM \"tournament_prediction\" WHERE username='"+userName+"' and tournament_year='"+tournamentYear+"' ;";
//
//        Connection conn = ConenctionPool.getConnection();
//        Statement statement = conn.createStatement();
//
//        statement.executeUpdate(sql);
//
//        conn.close();
//    }
}