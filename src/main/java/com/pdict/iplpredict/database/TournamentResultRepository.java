package com.pdict.iplpredict.database;
import com.pdict.iplpredict.entities.TournamentResult;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TournamentResultRepository {
    DatabaseInteraction databaseInteraction = new DatabaseInteraction();

    public TournamentResult getTournamentResult (Integer tournamentYear) throws SQLException {
        String sql = "SELECT * FROM \"tournament_user\" WHERE tournament_year='" + tournamentYear + "';";

        ResultSet resultSet = databaseInteraction.executeQuery(sql);
        resultSet.next();

        String orangeCap = resultSet.getString("orange_cap");
        String purpleCap = resultSet.getString("purple_cap");
        String firstTeam  = resultSet.getString("sf1");
        String secondTeam = resultSet.getString("sf2");
        String thirdTeam  = resultSet.getString("sf3");
        String fourthTeam = resultSet.getString("sf4");

        return new TournamentResult( tournamentYear , orangeCap , purpleCap , firstTeam , secondTeam , thirdTeam , fourthTeam );
    }

    public void addTournamentResult(TournamentResult tournamentResult) throws SQLException {
        String sql = "INSERT INTO \"tournament_result\" (tournament_year, orange_cap, purple_cap, sf1, sf2, sf3, sf4) VALUES ('"+tournamentResult.tournamentYear+"', '"+tournamentResult.orangeCap+"',  '"+tournamentResult.purpleCap+"',  '"+tournamentResult.firstTeam+"', '"+tournamentResult.secondTeam+"', '"+tournamentResult.thirdTeam+"', '"+tournamentResult.fourthTeam+"');";

        databaseInteraction.executeUpdate(sql);
    }

    public void updateTournamentResult(TournamentResult tournamentResult) throws SQLException{
        String sql = "UPDATE \"tournament_result\" SET orange_cap='"+tournamentResult.orangeCap+"', purple_cap='"+ tournamentResult.purpleCap+"', sf1='"+ tournamentResult.firstTeam+"', sf2='"+ tournamentResult.secondTeam+"', sf3='"+ tournamentResult.thirdTeam+"', sf4='"+ tournamentResult.fourthTeam+"' WHERE  tournament_year='"+ tournamentResult.tournamentYear+"' ;";

        databaseInteraction.executeUpdate(sql);
    }

    public void deleteTournamentResult( Integer tournamentYear) throws  SQLException{
        String sql = "DELETE FROM \"tournament_year\" WHERE tournament_year='"+tournamentYear+"' ;";

        databaseInteraction.executeUpdate(sql);
    }
}
