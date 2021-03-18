package com.pdict.iplpredict.database;

import com.pdict.iplpredict.entities.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {
    DatabaseInteraction databaseInteraction = new DatabaseInteraction();

    public User getUserById(Integer userId) throws SQLException {
        String sql = "SELECT * FROM \"user\" WHERE user_id=" + userId + ";";

        ResultSet resultSet = databaseInteraction.executeQuery(sql);
        resultSet.next();

        String username = resultSet.getString("username");
        String password = resultSet.getString("password");
        String fullName = resultSet.getString("full_name");
        String favTeam = resultSet.getString("fav_team");

        return new User(userId, username, password, fullName, favTeam);
    }

    public void insertUser(String username, String password, String fullName, String favTeam) throws SQLException {
        String sql = "INSERT INTO \"user\" (username, password, full_name, fav_team) VALUES ('"+username+"', '"+password+"', '"+fullName+"', '"+favTeam+"');";

        databaseInteraction.executeUpdate(sql);
    }

    public void updateUser(User user) throws SQLException {
        String sql = "UPDATE \"user\" SET username='"+user.username+"', password='"+user.password+"', full_name='"+user.fullName+"', fav_team='"+user.favTeam+"' WHERE user_id="+user.userId+";";

        databaseInteraction.executeUpdate(sql);
    }

    public void deleteUserById(Integer userId) throws SQLException {
        String sql = "DELETE FROM \"user\" WHERE user_id="+userId+";";

        databaseInteraction.executeUpdate(sql);
    }
}
