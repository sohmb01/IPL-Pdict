package com.pdict.iplpredict.database;

import com.pdict.iplpredict.entities.Team;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TeamRepository {

    public Team getTeamByTeamCode(String teamCode) throws SQLException {
        String sql = "SELECT * FROM \"team\" WHERE team_code='" + teamCode + "';";

        Connection conn = ConenctionPool.getConnection();
        Statement statement = conn.createStatement();

        try {
            ResultSet resultSet = statement.executeQuery(sql);
            if(resultSet.next()) {
                String teamName = resultSet.getString("team_name");

                return new Team(teamCode, teamName);
            } else {
                return null;
            }
        } finally {
            conn.close();
        }
    }

//    public void addTeam(Team team) throws SQLException {
//        String sql = "INSERT INTO \"team\" (team_code , team_name) VALUES ('"+team.teamCode+"', '"+team.teamName+"');";
//
//        Connection conn = ConenctionPool.getConnection();
//        Statement statement = conn.createStatement();
//
//        statement.executeUpdate(sql);
//
//        conn.close();
//    }
//
//    public void updateTeam(Team team) throws SQLException {
//        String sql = "UPDATE \"team\" SET team_name='"+team.teamName+"' WHERE team_code='"+team.teamCode+"';";
//
//        Connection conn = ConenctionPool.getConnection();
//        Statement statement = conn.createStatement();
//
//        statement.executeUpdate(sql);
//
//        conn.close();
//    }
//
//    public void deleteTeam(String teamCode) throws SQLException {
//        String sql = "DELETE FROM \"team\" WHERE team_code='"+teamCode+"';";
//
//        Connection conn = ConenctionPool.getConnection();
//        Statement statement = conn.createStatement();
//
//        statement.executeUpdate(sql);
//
//        conn.close();
//    }
}