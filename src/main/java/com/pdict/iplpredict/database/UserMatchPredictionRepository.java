package com.pdict.iplpredict.database;

import com.pdict.iplpredict.entities.UserMatchPrediction;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserMatchPredictionRepository {

    public List<UserMatchPrediction> getUserMatchPredictionsByMatchId(String matchId) throws SQLException {
        String sql = "SELECT mp.username, fav_team, match_id, team_win, team1_high, team1_low, team2_high, team2_low,"
                + " wickets FROM \"match_prediction\" mp JOIN \"user\" u ON mp.username=u.username WHERE " +
                "match_id=" + matchId + ";";

        Connection conn = ConenctionPool.getConnection();
        Statement statement = conn.createStatement();

        List<UserMatchPrediction> userMatchPredictions = new ArrayList<>();

        try {
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String userName = resultSet.getString("username");
                String favTeam = resultSet.getString("fav_team");
                String teamWin = resultSet.getString("team_win");
                Integer teamHigh1 = resultSet.getInt("team1_high");
                Integer teamLow1 = resultSet.getInt("team1_low");
                Integer teamHigh2 = resultSet.getInt("team2_high");
                Integer teamLow2 = resultSet.getInt("team2_low");
                Integer wickets = resultSet.getInt("wickets");

                userMatchPredictions.add(new UserMatchPrediction(userName, favTeam, matchId, teamWin, teamHigh1,
                        teamLow1, teamHigh2, teamLow2, wickets));
            }
        } finally {
            conn.close();
        }

        return userMatchPredictions;
    }

    public List<UserMatchPrediction> getUserSuperOverPredictionsByMatchIdAndUsername(String matchId, String userName) throws SQLException {
        String sql = "SELECT fav_team, mp.match_id as so_match_id, match_id, team_win, team1_high, team1_low, team2_high, team2_low,"
                + " wickets FROM \"match_prediction\" mp JOIN \"user\" u ON mp.username=u.username WHERE " +
                "match_id LIKE '" + matchId + "-so%' AND mp.username='"+userName+"';";

        Connection conn = ConenctionPool.getConnection();
        Statement statement = conn.createStatement();

        List<UserMatchPrediction> userMatchPredictions = new ArrayList<>();

        try {
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String superOverMatchId = resultSet.getString("so_match_id");
                String favTeam = resultSet.getString("fav_team");
                String teamWin = resultSet.getString("team_win");
                Integer teamHigh1 = resultSet.getInt("team1_high");
                Integer teamLow1 = resultSet.getInt("team1_low");
                Integer teamHigh2 = resultSet.getInt("team2_high");
                Integer teamLow2 = resultSet.getInt("team2_low");
                Integer wickets = resultSet.getInt("wickets");

                userMatchPredictions.add(new UserMatchPrediction(userName, favTeam, superOverMatchId, teamWin, teamHigh1,
                        teamLow1, teamHigh2, teamLow2, wickets));
            }
        } finally {
            conn.close();
        }

        return userMatchPredictions;
    }
}
