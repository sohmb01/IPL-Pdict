package com.pdict.iplpredict.database;

import java.sql.*;

public class DatabaseInteraction {
    public ResultSet executeQuery(String sql) throws SQLException {
        Connection conn = ConenctionPool.getConnection();
        Statement statement = conn.createStatement();
        return statement.executeQuery(sql);
    }

    public void executeUpdate(String sql) throws SQLException {
        Connection conn = ConenctionPool.getConnection();
        Statement statement = conn.createStatement();
        statement.executeUpdate(sql);
    }
}
