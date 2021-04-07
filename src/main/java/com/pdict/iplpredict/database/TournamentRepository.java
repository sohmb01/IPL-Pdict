package com.pdict.iplpredict.database;
import com.pdict.iplpredict.entities.Tournament;

import java.sql.*;
import java.util.Arrays;
import java.util.List;

public class TournamentRepository {

    public Tournament getTournament(Integer tournamentYear) throws SQLException {
        String sql = "SELECT * FROM \"tournament\" WHERE tournament_year="+tournamentYear+";";

        Connection conn = ConenctionPool.getConnection();
        Statement statement = conn.createStatement();

        try {
            ResultSet resultSet = statement.executeQuery(sql);

            if(resultSet.next()) {

                Integer tournamentStartDay = resultSet.getInt("tournament_start_day");
                Integer tournamentStartMonth = resultSet.getInt("tournament_start_month");
                Integer tournamentStartYear = resultSet.getInt("tournament_start_year");
                Integer tournamentEndDay = resultSet.getInt("tournament_end_day");
                Integer tournamentEndMonth = resultSet.getInt("tournament_end_month");
                Integer tournamentEndYear = resultSet.getInt("tournament_end_year");
                Boolean isFinished = resultSet.getBoolean("is_finished");
                String winningTeam = resultSet.getString("winning_team");
                String runnerUpTeam = resultSet.getString("runner_up_team");

                List<String> semiFinalists = null;
                Array semiFinalistsArray = resultSet.getArray("semi_finalists");
                if (semiFinalistsArray != null)
                    semiFinalists = Arrays.asList((String[]) resultSet.getArray("semi_finalists").getArray());

                String orangeCap = resultSet.getString("orange_cap");
                String purpleCap = resultSet.getString("purple_cap");

                return new Tournament(tournamentYear, tournamentStartDay, tournamentStartMonth, tournamentStartYear, tournamentEndDay, tournamentEndMonth, tournamentEndYear, isFinished, winningTeam, runnerUpTeam, semiFinalists, orangeCap, purpleCap);
            }
            else {
                return null;
            }
        } finally {
            conn.close();
        }
    }

//    public void addTournament(Tournament tournament) throws SQLException {
//        String semiFinalistsArraySQL = "'{\""+ tournament.semiFinalists.get(0)+"\",\""+ tournament.semiFinalists.get(1)+"\",\""+ tournament.semiFinalists.get(2)+"\",\""+ tournament.semiFinalists.get(3)+"\"}'";
//
//        String sql =
//                "INSERT INTO \"tournament\" (tournament_year, tournament_start_date, is_finished, tournament_end_date, winning_team, runner_up_team, semi_finalists, orange_cap, purple_cap) VALUES ("+tournament.tournamentYear+", '"+tournament.tournamentStartDate+"', '"+tournament.tournamentEndDate+"', '"+tournament.isFinished+"', '"+tournament.winningTeam+"', '"+ tournament.runnerUpTeam+"', "+semiFinalistsArraySQL+", '"+tournament.orangeCap+"', '"+tournament.purpleCap+"');";
//
//        Connection conn = ConenctionPool.getConnection();
//        Statement statement = conn.createStatement();
//
//        statement.executeUpdate(sql);
//
//        conn.close();
//    }

    public void updateTournament(Tournament tournament) throws SQLException{
        String semiFinalistsArraySQL = "'{\""+ tournament.semiFinalists.get(0)+"\",\""+ tournament.semiFinalists.get(1)+"\",\""+ tournament.semiFinalists.get(2)+"\",\""+ tournament.semiFinalists.get(3)+"\"}'";

        String sql = "UPDATE \"tournament\" SET tournament_start_day="+tournament.tournamentStartDay+", tournament_start_month="+tournament.tournamentStartMonth+", tournament_start_year="+tournament.tournamentStartYear+", tournament_end_day="+tournament.tournamentEndDay+", tournament_end_month="+tournament.tournamentEndMonth+", tournament_end_year="+tournament.tournamentEndYear+", is_finished='"+tournament.isFinished+"', winning_team='"+tournament.winningTeam+"', runner_up_team='"+tournament.runnerUpTeam+"', semi_finalists="+semiFinalistsArraySQL+", orange_cap='"+tournament.orangeCap+"', purple_cap='"+tournament.purpleCap+"' WHERE tournament_year="+tournament.tournamentYear+";";

        Connection conn = ConenctionPool.getConnection();
        Statement statement = conn.createStatement();

        try {
            statement.executeUpdate(sql);
        } finally {
            conn.close();
        }

    }

//    public void deleteTournament(Integer tournamentYear) throws  SQLException{
//        String sql = "DELETE FROM \"tournament\" WHERE tournament_year="+tournamentYear+";";
//
//        Connection conn = ConenctionPool.getConnection();
//        Statement statement = conn.createStatement();
//
//        statement.executeUpdate(sql);
//
//        conn.close();
//    }
}
