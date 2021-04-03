package com.pdict.iplpredict.database;

import com.pdict.iplpredict.entities.Match;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MatchRepository {

    public Match getMatchByMatchId(String matchId) throws SQLException {
        String sql = "SELECT * FROM \"match\" WHERE match_id='"+ matchId +"';";

        Connection conn = ConenctionPool.getConnection();
        Statement statement = conn.createStatement();

        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();

        Date matchDate = resultSet.getDate("match_date");
        Time matchStartTime = resultSet.getTime("match_start_time");
        Boolean isFinished = resultSet.getBoolean("is_finished");
        String matchType = resultSet.getString("match_type");
        String teamWin = resultSet.getString("team_win");
        String teamId1 = resultSet.getString("team1_id");
        String teamId2 = resultSet.getString("team2_id");
        Integer teamScore1 = resultSet.getInt("team1_score");
        Integer teamScore2 = resultSet.getInt("team2_score");
        Integer wickets = resultSet.getInt("wickets");

        conn.close();

        return new Match(matchId, matchDate, matchStartTime, isFinished, matchType, teamWin, teamId1, teamId2, teamScore1, teamScore2, wickets);
    }

    public List<Match> getAllMatches() throws SQLException {
        List<Match> matches = new ArrayList<Match>();

        String sql = "SELECT * FROM \"match\" ";

        Connection conn = ConenctionPool.getConnection();
        Statement statement = conn.createStatement();

        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            String matchId = resultSet.getString("match_id");
            Date matchDate = resultSet.getDate("match_date");
            Time matchStartTime = resultSet.getTime("match_start_time");
            Boolean isFinished = resultSet.getBoolean("is_finished");
            String matchType = resultSet.getString("match_type");
            String teamWin = resultSet.getString("team_win");
            String teamId1 = resultSet.getString("team1_id");
            String teamId2 = resultSet.getString("team2_id");
            Integer teamScore1 = resultSet.getInt("team1_score");
            Integer teamScore2 = resultSet.getInt("team2_score");
            Integer wickets = resultSet.getInt("wickets");

            matches.add(new Match(matchId, matchDate, matchStartTime, isFinished, matchType, teamWin, teamId1, teamId2, teamScore1, teamScore2, wickets));
        }

        conn.close();

        return matches;
    }

    public List<Match> getSuperOversOfMatch(String originalMatchId)  throws SQLException {
        List<Match> matches = new ArrayList<Match>();

        String sql = "SELECT * FROM \"match\" WHERE match_id LIKE '"+originalMatchId+"-so%'";

        Connection conn = ConenctionPool.getConnection();
        Statement statement = conn.createStatement();

        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            String matchId = resultSet.getString("match_id");
            Date matchDate = resultSet.getDate("match_date");
            Time matchStartTime = resultSet.getTime("match_start_time");
            Boolean isFinished = resultSet.getBoolean("is_finished");
            String matchType = resultSet.getString("match_type");
            String teamWin = resultSet.getString("team_win");
            String teamId1 = resultSet.getString("team1_id");
            String teamId2 = resultSet.getString("team2_id");
            Integer teamScore1 = resultSet.getInt("team1_score");
            Integer teamScore2 = resultSet.getInt("team2_score");
            Integer wickets = resultSet.getInt("wickets");

            matches.add(new Match(matchId, matchDate, matchStartTime, isFinished, matchType, teamWin, teamId1, teamId2, teamScore1, teamScore2, wickets));
        }

        conn.close();

        return matches;
    }

    public void insertMatch(Match match) throws SQLException {
        String sql = "INSERT INTO \"match\" (match_id,match_date,match_start_time,is_finished,match_type,team_win,team1_id,team2_id,team1_score,team2_score,wickets)" + "VALUES " + "('"+match.matchId+"', '"+match.matchDate+"', '"+match.matchStartTime+"', "+match.isFinished+", '"+match.matchType+"', '"+match.teamWin+"', '"+match.teamId1+"', '"+match.teamId2+"', "+match.teamScore1+", "+match.teamScore2+","+match.wickets+")";

        Connection conn = ConenctionPool.getConnection();
        Statement statement = conn.createStatement();

        statement.executeUpdate(sql);

        conn.close();
    }

    public void updateMatch(Match match) throws SQLException {
        String sql = "UPDATE \"match\" SET match_id='"+match.matchId+"', match_date='"+match.matchDate+"', match_start_time='"+match.matchStartTime+"', is_finished="+match.isFinished+", match_type='"+match.matchType+"', team_win='"+match.teamWin+"', team1_id='"+match.teamId1+"', team2_id='"+match.teamId2+"', team1_score="+match.teamScore1+", team2_score="+match.teamScore2+", wickets="+match.wickets+" WHERE match_id='"+match.matchId+"';";

        Connection conn = ConenctionPool.getConnection();
        Statement statement = conn.createStatement();

        statement.executeUpdate(sql);

        conn.close();
    }
}
