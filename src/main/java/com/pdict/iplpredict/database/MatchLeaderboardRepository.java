package com.pdict.iplpredict.database;

import com.pdict.iplpredict.entities.UserPoints;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MatchLeaderboardRepository {
    public List<UserPoints> getMatchLeaderboard(String matchId) throws SQLException {
        String sql = "SELECT username, points FROM \"match_points\" WHERE match_id='"+matchId+"' ORDER BY points DESC;";

        Connection conn = ConenctionPool.getConnection();
        Statement statement = conn.createStatement();

        List<UserPoints> pointsList = new ArrayList<>();

        try {
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String username = resultSet.getString("username");
                Integer matchPoints = resultSet.getInt("points");
                pointsList.add(new UserPoints(username, matchPoints));
            }
        } finally {
            conn.close();
        }

        return pointsList;
    }
}
