package com.pdict.iplpredict.database;

import com.pdict.iplpredict.entities.UserTournamentPrediction;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserTournamentPredictionRepository {

    public List<UserTournamentPrediction> getUserTournamentPredictionsByTournamentYear(Integer tournamentYear) throws SQLException {
        String sql = "SELECT tp.username, fav_team, winning_team, semi_finalists, orange_caps, purple_caps FROM " +
                "\"tournament_prediction\" tp JOIN \"user\" u ON tp.username=u.username WHERE " + "tournament_year=" + tournamentYear + ";";

        Connection conn = ConenctionPool.getConnection();
        Statement statement = conn.createStatement();

        List<UserTournamentPrediction> userTournamentPredictions = new ArrayList<>();

        try {
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String userName = resultSet.getString("username");
                String favTeam = resultSet.getString("fav_team");
                String winningTeam = resultSet.getString("winning_team");
                List<String> semiFinalists = Arrays.asList((String[]) resultSet.getArray("semi_finalists").getArray());
                List<String> orangeCaps = Arrays.asList((String[]) resultSet.getArray("orange_caps").getArray());
                List<String> purpleCaps = Arrays.asList((String[]) resultSet.getArray("purple_caps").getArray());

                userTournamentPredictions.add(new UserTournamentPrediction(userName, favTeam, tournamentYear, winningTeam
                        , semiFinalists, orangeCaps, purpleCaps));
            }
        } finally {
            conn.close();
        }

        return userTournamentPredictions;
    }
}
