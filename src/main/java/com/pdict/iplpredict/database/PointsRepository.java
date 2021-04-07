package com.pdict.iplpredict.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class PointsRepository {

    public void insertMatchPoints(String userName, String matchId, Integer points) throws SQLException {
        String sql = "INSERT INTO \"match_points\" VALUES ('"+userName+"', '"+matchId+"', "+points+");";

        Connection conn = ConenctionPool.getConnection();
        Statement statement = conn.createStatement();

        try {
            statement.executeUpdate(sql);
        } finally {
            conn.close();
        }
    }

    public void insertTournamentPoints(String userName, Integer tournamentYear, Integer points) throws SQLException {
        String sql = "INSERT INTO \"tournament_points\" VALUES ('"+userName+"', "+tournamentYear+", "+points+");";

        Connection conn = ConenctionPool.getConnection();
        Statement statement = conn.createStatement();

        try {
            statement.executeUpdate(sql);
        } finally {
            conn.close();
        }
    }
}
