package com.pdict.iplpredict.database;

import com.pdict.iplpredict.entities.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {
    DatabaseInteraction databaseInteraction = new DatabaseInteraction();

    public User getUserByUserName(String userName) throws SQLException {
        String sql = "SELECT * FROM \"user\" WHERE userName='" + userName + "';";

        ResultSet resultSet = databaseInteraction.executeQuery(sql);
        resultSet.next();

        Integer userId = resultSet.getInt("user_id");
        String password = resultSet.getString("password");
        String fullName = resultSet.getString("full_name");
        String favTeam = resultSet.getString("fav_team");

        return new User(userName, password, fullName, favTeam);
    }

    public void insertUser(User user) throws SQLException {
        String sql = "INSERT INTO \"user\" (userName, password, full_name, fav_team) VALUES ('"+user.userName+"', '"+user.password+"', '"+user.fullName+"', '"+user.favTeam+"');";

        databaseInteraction.executeUpdate(sql);
    }

    public void updateUser(User user) throws SQLException {
        String sql = "UPDATE \"user\" SET password='"+user.password+"', full_name='"+user.fullName+"', fav_team='"+user.favTeam+"' WHERE username='"+user.userName+"';";

        databaseInteraction.executeUpdate(sql);
    }

    public void deleteUserByUserName(String userName) throws SQLException {
        String sql = "DELETE FROM \"user\" WHERE username='"+userName+"';";

        databaseInteraction.executeUpdate(sql);
    }
}
