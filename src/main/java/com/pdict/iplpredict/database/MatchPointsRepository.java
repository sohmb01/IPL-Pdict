package com.pdict.iplpredict.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class MatchPointsRepository {

    public void insertPoints(String userName, String matchId, Integer points) throws SQLException {
        String sql = "INSERT INTO \"match_points\" VALUES ('"+userName+"', '"+matchId+"', "+points+");";

        Connection conn = ConenctionPool.getConnection();
        Statement statement = conn.createStatement();

        statement.executeUpdate(sql);

        conn.close();
    }
}
