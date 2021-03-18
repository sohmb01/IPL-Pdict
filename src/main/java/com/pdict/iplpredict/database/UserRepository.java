package com.pdict.iplpredict.database;

import com.pdict.iplpredict.pojo.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {
    DatabaseInteraction databaseInteraction = new DatabaseInteraction();

    public User getUserById(Integer userId) throws SQLException {
        String sql = "SELECT * FROM USER WHERE user_id=" + userId + ";";

        ResultSet resultSet = databaseInteraction.executeQuery(sql);
        resultSet.next();

        String username = resultSet.getString("username");
        String password = resultSet.getString("password");
        String fullName = resultSet.getString("full_name");
        String favTeam = resultSet.getString("fav_team");

        return new User(userId, username, password, fullName, favTeam);
    }

    public void insertUser(String username, String password, String fullName, String favTeam) {
        String sql = "INSERT INTO USER(username, password, full_name, fav_team) VALUES('"+username+"', '"+password+"', '"+fullName+"', '"+favTeam+"');";

        databaseInteraction.executeUpdate(sql);
    }

    public void updateUser(User user) {
        String sql = "UPDATE USER SET username='"+user.username+"', password='"+user.password+"', full_name='"+user.fullName+"', fav_team='"+user.favTeam+"' WHERE user_id="+user.userId+";";

        databaseInteraction.executeUpdate(sql);
    }

    public void deleteUserById(Integer userId) {
        String sql = "DELETE FROM USER WHERE user_id="+userId+";";

        databaseInteraction.executeUpdate(sql);
    }
}
