package com.pdict.iplpredict.database;

import com.pdict.iplpredict.entities.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserRepository {

    public User getUserByUserName(String userName) throws SQLException {
        String sql = "SELECT * FROM \"user\" WHERE userName='" + userName + "';";

        Connection conn = ConenctionPool.getConnection();
        Statement statement = conn.createStatement();

        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();

        String password = resultSet.getString("password");
        String fullName = resultSet.getString("full_name");
        String favTeam = resultSet.getString("fav_team");

        conn.close();

        return new User(userName, password, fullName, favTeam);
    }

    public void insertUser(User user) throws SQLException {
        String sql = "INSERT INTO \"user\" (userName, password, full_name, fav_team) VALUES ('"+user.userName+"', '"+user.password+"', '"+user.fullName+"', '"+user.favTeam+"');";

        Connection conn = ConenctionPool.getConnection();
        Statement statement = conn.createStatement();

        statement.executeUpdate(sql);

        conn.close();
    }

    public void updateUser(User user) throws SQLException {
        String sql = "UPDATE \"user\" SET password='"+user.password+"', full_name='"+user.fullName+"', fav_team='"+user.favTeam+"' WHERE username='"+user.userName+"';";

        Connection conn = ConenctionPool.getConnection();
        Statement statement = conn.createStatement();

        statement.executeUpdate(sql);

        conn.close();
    }

    public void deleteUserByUserName(String userName) throws SQLException {
        String sql = "DELETE FROM \"user\" WHERE username='"+userName+"';";

        Connection conn = ConenctionPool.getConnection();
        Statement statement = conn.createStatement();

        statement.executeUpdate(sql);

        conn.close();
    }
}
