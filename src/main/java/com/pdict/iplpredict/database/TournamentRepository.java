package com.pdict.iplpredict.database;
import com.pdict.iplpredict.entities.Tournament;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class TournamentRepository {
    DatabaseInteraction databaseInteraction = new DatabaseInteraction();

    public Tournament getTournament(Integer tournamentYear) throws SQLException {
        String sql = "SELECT * FROM \"tournament\" WHERE tournament_year="+tournamentYear+";";
        System.out.println(sql);

        ResultSet resultSet = databaseInteraction.executeQuery(sql);
        resultSet.next();

        String winningTeam = resultSet.getString("winning_team");
        String runnerUpTeam  =resultSet.getString("runner_up_team");
        List<String> semiFinalists = Arrays.asList((String [])resultSet.getArray("semi_finalists").getArray());
        String orangeCap = resultSet.getString("orange_cap");
        String purpleCap = resultSet.getString("purple_cap");

        return new Tournament(tournamentYear, winningTeam, runnerUpTeam, semiFinalists, orangeCap, purpleCap);
    }

    public void addTournament(Tournament tournament) throws SQLException {
        String semiFinalistsArraySQL = "'{\""+ tournament.semiFinalists.get(0)+"\",\""+ tournament.semiFinalists.get(1)+"\",\""+ tournament.semiFinalists.get(2)+"\",\""+ tournament.semiFinalists.get(3)+"\"}'";

        String sql =
                "INSERT INTO \"tournament\" (tournament_year, winning_team, runner_up_team, semi_finalists, orange_cap, purple_cap) VALUES ("+tournament.tournamentYear+", '"+ tournament.winningTeam+"', '"+ tournament.runnerUpTeam+"', "+semiFinalistsArraySQL+", '"+tournament.orangeCap+"', '"+tournament.purpleCap+"');";
        System.out.println(sql);

        databaseInteraction.executeUpdate(sql);
    }

    public void updateTournament(Tournament tournament) throws SQLException{
        String semiFinalistsArraySQL = "'{\""+ tournament.semiFinalists.get(0)+"\",\""+ tournament.semiFinalists.get(1)+"\",\""+ tournament.semiFinalists.get(2)+"\",\""+ tournament.semiFinalists.get(3)+"\"}'";

        String sql = "UPDATE \"tournament\" SET winning_team='"+tournament.winningTeam+"', runner_up_team='"+tournament.runnerUpTeam+"', semi_finalists="+semiFinalistsArraySQL+", orange_cap='"+tournament.orangeCap+"', purple_cap='"+tournament.purpleCap+"' WHERE tournament_year="+tournament.tournamentYear+";";
        System.out.println(sql);

        databaseInteraction.executeUpdate(sql);
    }

    public void deleteTournament(Integer tournamentYear) throws  SQLException{
        String sql = "DELETE FROM \"tournament\" WHERE tournament_year="+tournamentYear+";";
        System.out.println(sql);

        databaseInteraction.executeUpdate(sql);
    }
}
