package com.pdict.iplpredict.database;

import com.pdict.iplpredict.entities.SuperOver;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SuperOverRepository {

    public List<SuperOver> getSuperOversOfMatch(String matchId)  throws SQLException {
        List<SuperOver> superOvers = new ArrayList<>();

        String sql = "SELECT * FROM \"superover\" WHERE match_id='"+matchId+"';";

        Connection conn = ConenctionPool.getConnection();
        Statement statement = conn.createStatement();

        try {
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Integer superOverNumber = resultSet.getInt("superover_number");
                Integer superOverStartMinute = resultSet.getInt("superover_start_minute");
                Integer superOverStartHour = resultSet.getInt("superover_start_hour");
                Integer superOverStartDay = resultSet.getInt("superover_start_day");
                Integer superOverStartMonth = resultSet.getInt("superover_start_month");
                Integer superOverStartYear = resultSet.getInt("superover_start_year");
                Boolean isFinished = resultSet.getBoolean("is_finished");
                String teamWin = resultSet.getString("team_win");
                String teamId1 = resultSet.getString("team1_id");
                String teamId2 = resultSet.getString("team2_id");
                Integer teamScore1 = resultSet.getInt("team1_score");
                Integer teamScore2 = resultSet.getInt("team2_score");
                Integer wickets = resultSet.getInt("wickets");

                superOvers.add(new SuperOver(superOverNumber, matchId, superOverStartMinute, superOverStartHour, superOverStartDay, superOverStartMonth, superOverStartYear, isFinished, teamWin, teamId1, teamId2, teamScore1, teamScore2, wickets));
            }
        } finally {
            conn.close();
        }

        return superOvers;
    }

    public void upsertSuperOver(SuperOver superOver) throws SQLException {
        String updateSql = "UPDATE SET superover_start_minute="+superOver.superOverStartMinute+", superover_start_hour="+superOver.superOverStartHour+", superover_start_day="+superOver.superOverStartDay+", superover_start_month="+superOver.superOverStartMonth+", superover_start_year="+superOver.superOverStartYear+", is_finished='"+superOver.isFinished+"', team_win='"+superOver.teamWin+"', team1_id='"+superOver.teamId1+"', team2_id='"+superOver.teamId2+"', team1_score="+superOver.teamScore1+", team2_score="+superOver.teamScore2+", wickets="+superOver.wickets+";";

        String sql = "INSERT INTO \"superover\" VALUES ("+superOver.superOverNumber+", '"+superOver.matchId+"', "+superOver.superOverStartMinute+", "+superOver.superOverStartHour+", "+superOver.superOverStartDay+", "+superOver.superOverStartMonth+", "+superOver.superOverStartYear+", '"+superOver.isFinished+"', '"+superOver.teamWin+"', '"+superOver.teamId1+"', '"+superOver.teamId2+"', "+superOver.teamScore1+", "+superOver.teamScore2+", "+superOver.wickets+") ON CONFLICT(superover_number,match_id) DO "+updateSql;

        System.out.println(sql);
        Connection conn = ConenctionPool.getConnection();
        Statement statement = conn.createStatement();

        try {
            statement.executeUpdate(sql);
        }
        finally {
            conn.close();
        }
    }
}
