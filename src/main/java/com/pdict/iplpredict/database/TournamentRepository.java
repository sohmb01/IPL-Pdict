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

        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();

        Date tournamentStartDate = resultSet.getDate("tournament_start_date");
        Date tournamentEndDate = resultSet.getDate("tournament_end_date");
        Boolean isFinished = resultSet.getBoolean("is_finished");
        String winningTeam = resultSet.getString("winning_team");
        String runnerUpTeam  =resultSet.getString("runner_up_team");
        List<String> semiFinalists = Arrays.asList((String [])resultSet.getArray("semi_finalists").getArray());
        String orangeCap = resultSet.getString("orange_cap");
        String purpleCap = resultSet.getString("purple_cap");

        conn.close();

        return new Tournament(tournamentYear, tournamentStartDate, tournamentEndDate, isFinished, winningTeam, runnerUpTeam, semiFinalists, orangeCap, purpleCap);
    }

    public void addTournament(Tournament tournament) throws SQLException {
        String semiFinalistsArraySQL = "'{\""+ tournament.semiFinalists.get(0)+"\",\""+ tournament.semiFinalists.get(1)+"\",\""+ tournament.semiFinalists.get(2)+"\",\""+ tournament.semiFinalists.get(3)+"\"}'";

        String sql =
                "INSERT INTO \"tournament\" (tournament_year, tournament_start_date, is_finished, tournament_end_date, winning_team, runner_up_team, semi_finalists, orange_cap, purple_cap) VALUES ("+tournament.tournamentYear+", '"+tournament.tournamentStartDate+"', '"+tournament.tournamentEndDate+"', '"+tournament.isFinished+"', '"+tournament.winningTeam+"', '"+ tournament.runnerUpTeam+"', "+semiFinalistsArraySQL+", '"+tournament.orangeCap+"', '"+tournament.purpleCap+"');";

        Connection conn = ConenctionPool.getConnection();
        Statement statement = conn.createStatement();

        statement.executeUpdate(sql);

        conn.close();
    }

    public void updateTournament(Tournament tournament) throws SQLException{
        String semiFinalistsArraySQL = "'{\""+ tournament.semiFinalists.get(0)+"\",\""+ tournament.semiFinalists.get(1)+"\",\""+ tournament.semiFinalists.get(2)+"\",\""+ tournament.semiFinalists.get(3)+"\"}'";

        String sql = "UPDATE \"tournament\" SET tournament_start_date='"+tournament.tournamentStartDate+"', tournament_end_date='"+tournament.tournamentEndDate+"', is_finished='"+tournament.isFinished+"', winning_team='"+tournament.winningTeam+"', runner_up_team='"+tournament.runnerUpTeam+"', semi_finalists="+semiFinalistsArraySQL+", orange_cap='"+tournament.orangeCap+"', purple_cap='"+tournament.purpleCap+"' WHERE tournament_year="+tournament.tournamentYear+";";

        Connection conn = ConenctionPool.getConnection();
        Statement statement = conn.createStatement();

        statement.executeUpdate(sql);

        conn.close();
    }

    public void deleteTournament(Integer tournamentYear) throws  SQLException{
        String sql = "DELETE FROM \"tournament\" WHERE tournament_year="+tournamentYear+";";

        Connection conn = ConenctionPool.getConnection();
        Statement statement = conn.createStatement();

        statement.executeUpdate(sql);

        conn.close();
    }
}
