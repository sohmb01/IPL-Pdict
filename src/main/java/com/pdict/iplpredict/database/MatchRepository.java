package com.pdict.iplpredict.database;

import com.pdict.iplpredict.entities.Match;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MatchRepository {
    DatabaseInteraction databaseInteraction = new DatabaseInteraction();

    public Match getMatchByMatchId(Integer matchId) throws SQLException {
        String sql = "SELECT * FROM \"match\" WHERE matchId="+ matchId +";";

        ResultSet resultSet = databaseInteraction.executeQuery(sql);
        resultSet.next();

        Integer tournamentYear = resultSet.getInt("tournament_year");
        String teamWin = resultSet.getString("team_win");
        String teamId1 = resultSet.getString("team1_id");
        String teamId2 = resultSet.getString("team2_id");
        Integer teamScore1 = resultSet.getInt("team1_score");
        Integer teamScore2 = resultSet.getInt("team2_score");
        Integer wickets = resultSet.getInt("wickets");

        return new Match(matchId,tournamentYear,teamWin,teamId1,teamId2,teamScore1,teamScore2,wickets);
    }

    public List<Match> getAllMatches() throws SQLException {
        List<Match> matches = new ArrayList<Match>();

        String sql = "SELECT * FROM \"match\" ";
        ResultSet resultSet = databaseInteraction.executeQuery(sql);

        while (resultSet.next()) {
            Integer matchId = resultSet.getInt("match_id");
            Integer tournamentYear = resultSet.getInt("tournament_year");
            String teamWin = resultSet.getString("team_win");
            String teamId1 = resultSet.getString("team1_id");
            String teamId2 = resultSet.getString("team2_id");
            Integer teamScore1 = resultSet.getInt("team1_score");
            Integer teamScore2 = resultSet.getInt("team2_score");
            Integer wickets = resultSet.getInt("wickets");

            matches.add(new Match(matchId,tournamentYear,teamWin,teamId1,teamId2,teamScore1,teamScore2,wickets));
        }

        return matches;
    }

    public void insertMatch(Match match) throws SQLException {
        String sql = "INSERT INTO \"match\" (match_id,tournament_year,team_win,team1_id,team2_id,team1_score,team2_score,wickets))" + "VALUES " + "("+match.matchId+", "+match.tournamentYear+", '"+match.teamWin+"', '"+match.teamId1+"', '"+match.teamId2+"', "+match.teamScore1+", "+match.teamScore2+","+match.wickets+")";

        databaseInteraction.executeUpdate(sql);
    }


    public void updateMatch(Match match) throws SQLException {
        String sql = "UPDATE \"match\" SET match_id="+match.matchId+", tournament_year="+match.tournamentYear+", team_win='"+match.teamWin+"', team1_id='"+match.teamId1+"', team2_id='"+match.teamId2+"', team1_score="+match.teamScore1+", team2_score="+match.teamScore2+", wickets="+match.wickets+" WHERE match_id="+match.matchId+";";

        databaseInteraction.executeUpdate(sql);
    }
}
