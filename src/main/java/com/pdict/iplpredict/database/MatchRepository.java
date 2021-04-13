package com.pdict.iplpredict.database;

import com.pdict.iplpredict.entities.Match;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MatchRepository {

    public Match getMatchByMatchId(String matchId) throws SQLException {
        String sql = "SELECT * FROM \"match\" WHERE match_id='"+ matchId +"';";

        Connection conn = ConenctionPool.getConnection();
        Statement statement = conn.createStatement();

        try {
            ResultSet resultSet = statement.executeQuery(sql);

            if(resultSet.next()) {

                Integer matchStartMinute = resultSet.getInt("match_start_minute");
                Integer matchStartHour = resultSet.getInt("match_start_hour");
                Integer matchStartDay = resultSet.getInt("match_start_day");
                Integer matchStartMonth = resultSet.getInt("match_start_month");
                Integer matchStartYear = resultSet.getInt("match_start_year");
                Boolean isFinished = resultSet.getBoolean("is_finished");
                String matchType = resultSet.getString("match_type");
                String teamWin = resultSet.getString("team_win");
                String teamId1 = resultSet.getString("team1_id");
                String teamId2 = resultSet.getString("team2_id");
                Integer teamScore1 = resultSet.getInt("team1_score");
                Integer teamScore2 = resultSet.getInt("team2_score");
                Integer wickets = resultSet.getInt("wickets");

                return new Match(matchId, matchStartMinute, matchStartHour, matchStartDay, matchStartMonth, matchStartYear, isFinished, matchType, teamWin, teamId1, teamId2, teamScore1, teamScore2, wickets);
            }
            else {
                return null;
            }
        } finally {
            conn.close();
        }

    }

    public List<Match> getAllMatches() throws SQLException {
        String sql = "SELECT * FROM \"match\" ";

        Connection conn = ConenctionPool.getConnection();
        Statement statement = conn.createStatement();

        List<Match> matches = new ArrayList<Match>();
        try {
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String matchId = resultSet.getString("match_id");
                Integer matchStartMinute = resultSet.getInt("match_start_minute");
                Integer matchStartHour = resultSet.getInt("match_start_hour");
                Integer matchStartDay = resultSet.getInt("match_start_day");
                Integer matchStartMonth = resultSet.getInt("match_start_month");
                Integer matchStartYear = resultSet.getInt("match_start_year");
                Boolean isFinished = resultSet.getBoolean("is_finished");
                String matchType = resultSet.getString("match_type");
                String teamWin = resultSet.getString("team_win");
                String teamId1 = resultSet.getString("team1_id");
                String teamId2 = resultSet.getString("team2_id");
                Integer teamScore1 = resultSet.getInt("team1_score");
                Integer teamScore2 = resultSet.getInt("team2_score");
                Integer wickets = resultSet.getInt("wickets");

                matches.add(new Match(matchId, matchStartMinute, matchStartHour, matchStartDay, matchStartMonth, matchStartYear, isFinished, matchType, teamWin, teamId1, teamId2, teamScore1, teamScore2, wickets));
            }
        } finally {
            conn.close();
        }

        return matches;
    }

    public List<Match> getFinishedMatches() throws SQLException{
        String sql = "SELECT * FROM \"match\" WHERE is_finished=true";

        Connection conn = ConenctionPool.getConnection();
        Statement statement = conn.createStatement();

        List<Match> matches = new ArrayList<Match>();
        try {
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String matchId = resultSet.getString("match_id");
                Integer matchStartMinute = resultSet.getInt("match_start_minute");
                Integer matchStartHour = resultSet.getInt("match_start_hour");
                Integer matchStartDay = resultSet.getInt("match_start_day");
                Integer matchStartMonth = resultSet.getInt("match_start_month");
                Integer matchStartYear = resultSet.getInt("match_start_year");
                Boolean isFinished = resultSet.getBoolean("is_finished");
                String matchType = resultSet.getString("match_type");
                String teamWin = resultSet.getString("team_win");
                String teamId1 = resultSet.getString("team1_id");
                String teamId2 = resultSet.getString("team2_id");
                Integer teamScore1 = resultSet.getInt("team1_score");
                Integer teamScore2 = resultSet.getInt("team2_score");
                Integer wickets = resultSet.getInt("wickets");

                matches.add(new Match(matchId, matchStartMinute, matchStartHour, matchStartDay, matchStartMonth, matchStartYear, isFinished, matchType, teamWin, teamId1, teamId2, teamScore1, teamScore2, wickets));
            }
        } finally {
            conn.close();
        }

        matches.sort((a,b)-> {
            return LocalDateTime.of(a.matchStartYear, a.matchStartMonth, a.matchStartDay, a.matchStartHour, a.matchStartMinute)
                    .isBefore(LocalDateTime.of(b.matchStartYear, b.matchStartMonth, b.matchStartDay, b.matchStartHour, b.matchStartMinute)) ? -1 : 1;
        });

        return matches;
    }

//    public List<Match> getSuperOversOfMatch(String originalMatchId)  throws SQLException {
//        List<Match> matches = new ArrayList<Match>();
//
//        String sql = "SELECT * FROM \"match\" WHERE match_id LIKE '"+originalMatchId+"-so%'";
//
//        Connection conn = ConenctionPool.getConnection();
//        Statement statement = conn.createStatement();
//
//        try {
//            ResultSet resultSet = statement.executeQuery(sql);
//
//            while (resultSet.next()) {
//                String matchId = resultSet.getString("match_id");
//                Integer matchStartMinute = resultSet.getInt("match_start_minute");
//                Integer matchStartHour = resultSet.getInt("match_start_hour");
//                Integer matchStartDay = resultSet.getInt("match_start_day");
//                Integer matchStartMonth = resultSet.getInt("match_start_month");
//                Integer matchStartYear = resultSet.getInt("match_start_year");
//                Boolean isFinished = resultSet.getBoolean("is_finished");
//                String matchType = resultSet.getString("match_type");
//                String teamWin = resultSet.getString("team_win");
//                String teamId1 = resultSet.getString("team1_id");
//                String teamId2 = resultSet.getString("team2_id");
//                Integer teamScore1 = resultSet.getInt("team1_score");
//                Integer teamScore2 = resultSet.getInt("team2_score");
//                Integer wickets = resultSet.getInt("wickets");
//
//                matches.add(new Match(matchId, matchStartMinute, matchStartHour, matchStartDay, matchStartMonth, matchStartYear, isFinished, matchType, teamWin, teamId1, teamId2, teamScore1, teamScore2, wickets));
//            }
//        } finally {
//            conn.close();
//        }
//
//        return matches;
//    }

//    public void insertMatch(Match match) throws SQLException {
//        String sql = "INSERT INTO \"match\" (match_id,match_date,match_start_time,is_finished,match_type,team_win,team1_id,team2_id,team1_score,team2_score,wickets)" + "VALUES " + "('"+match.matchId+"', '"+match.matchDate+"', '"+match.matchStartTime+"', "+match.isFinished+", '"+match.matchType+"', '"+match.teamWin+"', '"+match.teamId1+"', '"+match.teamId2+"', "+match.teamScore1+", "+match.teamScore2+","+match.wickets+")";
//
//        Connection conn = ConenctionPool.getConnection();
//        Statement statement = conn.createStatement();
//
//        try {
//            statement.executeUpdate(sql);
//        } finally {
//            conn.close();
//        }
//    }
//
//    public void updateMatch(Match match) throws SQLException {
//        String sql = "UPDATE \"match\" SET match_start_minute="+match.matchStartMinute+", match_start_hour="+match.matchStartHour+", match_start_day="+match.matchStartDay+", match_start_month="+match.matchStartMonth+", match_start_year="+match.matchStartYear+", is_finished="+match.isFinished+", match_type='"+match.matchType+"', team_win='"+match.teamWin+"', team1_id='"+match.teamId1+"', team2_id='"+match.teamId2+"', team1_score="+match.teamScore1+", team2_score="+match.teamScore2+", wickets="+match.wickets+" WHERE match_id='"+match.matchId+"';";
//
//        Connection conn = ConenctionPool.getConnection();
//        Statement statement = conn.createStatement();
//
//        try {
//            statement.executeUpdate(sql);
//        } finally {
//            conn.close();
//        }
//    }

    public void upsertMatch(Match match) throws SQLException {
        String updateSql = "UPDATE SET match_start_minute="+match.matchStartMinute+", match_start_hour="+match.matchStartHour+", match_start_day="+match.matchStartDay+", match_start_month="+match.matchStartMonth+", match_start_year="+match.matchStartYear+", is_finished='"+match.isFinished+"', match_type='"+match.matchType+"', team_win='"+match.teamWin+"', team1_id='"+match.teamId1+"', team2_id='"+match.teamId2+"', team1_score="+match.teamScore1+", team2_score="+match.teamScore2+", wickets="+ match.wickets+";";

        String sql = "INSERT INTO \"match\" VALUES ('"+match.matchId+"', "+match.matchStartMinute+", "+match.matchStartHour+", "+match.matchStartDay+", "+match.matchStartMonth+", "+match.matchStartYear+", '"+match.isFinished+"', '"+match.matchType+"', '"+match.teamWin+"', '"+match.teamId1+"', '"+match.teamId2+"', "+match.teamScore1+", "+match.teamScore2+", "+ match.wickets+") ON CONFLICT(match_id) DO "+updateSql;

        Connection conn = ConenctionPool.getConnection();
        Statement statement = conn.createStatement();

        try {
            statement.executeUpdate(sql);
        }
        finally {
            conn.close();
        }
    }
}
