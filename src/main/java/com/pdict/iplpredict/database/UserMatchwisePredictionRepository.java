package com.pdict.iplpredict.database;

import com.pdict.iplpredict.entities.UserMatchwisePrediction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserMatchwisePredictionRepository {
    DatabaseInteraction databaseInteraction = new DatabaseInteraction();

    public List<UserMatchwisePrediction> getUserMatchwisePredictionsByMatchId(Integer matchId) throws SQLException {
        String sql = "SELECT mp.username, fav_team, match_id, team_win, team1_high, team1_low, team2_high, team2_low,"
                + " wickets FROM \"matchwise_prediction\" mp JOIN \"user\" u ON mp.username=u.username where " +
                "match_id=" + matchId + ";";

        ResultSet resultSet = databaseInteraction.executeQuery(sql);
        List<UserMatchwisePrediction> userMatchwisePredictions = new ArrayList<>();

        while (resultSet.next()) {
            String userName = resultSet.getString("username");
            String favTeam = resultSet.getString("fav_team");
            String teamWin = resultSet.getString("team_win");
            Integer teamHigh1 = resultSet.getInt("team1_high");
            Integer teamLow1 = resultSet.getInt("team1_low");
            Integer teamHigh2 = resultSet.getInt("team2_high");
            Integer teamLow2 = resultSet.getInt("team2_low");
            Integer wickets = resultSet.getInt("wickets");

            userMatchwisePredictions.add(new UserMatchwisePrediction(userName, favTeam, matchId, teamWin, teamHigh1,
                    teamLow1, teamHigh2, teamLow2, wickets));
        }

        return userMatchwisePredictions;
    }
}
