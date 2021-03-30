package com.pdict.iplpredict.database;

import com.pdict.iplpredict.entities.UserPoints;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TournamentBonusLeaderboardRepository {
    public List<UserPoints> getTournamentBonusLeaderboard(Integer tournamentYear) throws SQLException {
        String sql = "SELECT username, points FROM \"tournament_points\" WHERE tournament_year="+tournamentYear+" ORDER BY points DESC;";

        Connection conn = ConenctionPool.getConnection();
        Statement statement = conn.createStatement();

        ResultSet resultSet = statement.executeQuery(sql);
        List<UserPoints> pointsList = new ArrayList<>();

        while (resultSet.next()){
            String username = resultSet.getString("username");
            Integer tournamentBonusPoints = resultSet.getInt("points");
            pointsList.add(new UserPoints(username,tournamentBonusPoints));
        }

        conn.close();

        return pointsList;
    }
}
