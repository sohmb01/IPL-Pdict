package com.pdict.iplpredict.database;

import java.sql.*;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

public class LoginSessionRepository {

    public void createNewToken(String username, String currentAuthToken, Timestamp createdTimestamp) throws SQLException {
        String updateSql = "UPDATE SET current_auth_token='"+currentAuthToken+"', created_timestamp='"+createdTimestamp+"';";

        String sql = "INSERT INTO \"login_session\" (username, current_auth_token, created_timestamp) VALUES ('"+username+"', '"+currentAuthToken+"', '"+createdTimestamp+"') ON CONFLICT(username) DO "+updateSql;

        Connection conn = ConenctionPool.getConnection();
        Statement statement = conn.createStatement();

        statement.executeUpdate(sql);

        conn.close();
    }

    public String getActiveToken(String username) throws SQLException {
        String sql = "SELECT * FROM \"login_session\" WHERE username='"+username+"';";

        Connection conn = ConenctionPool.getConnection();
        Statement statement = conn.createStatement();

        ResultSet resultSet = statement.executeQuery(sql);

        if(!resultSet.next())
            return null;
        else {
           Timestamp createdTimestamp = resultSet.getTimestamp("created_timestamp");
           return  isTimeDifferenceLessThan(createdTimestamp, 120) ? resultSet.getString("current_auth_token") : null;
        }
    }

    private Boolean isTimeDifferenceLessThan(Timestamp timestamp, Integer minutes) {
        return ChronoUnit.MINUTES.between(timestamp.toInstant(), Instant.now()) < minutes;
    }
}
