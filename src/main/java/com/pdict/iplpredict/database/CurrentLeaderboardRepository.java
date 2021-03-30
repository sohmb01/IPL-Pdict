package com.pdict.iplpredict.database;

import com.pdict.iplpredict.entities.Tournament;
import com.pdict.iplpredict.entities.UserPoints;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class CurrentLeaderboardRepository {
    TournamentRepository tournamentRepository = new TournamentRepository();

    public Integer getBonusPoints(String username) throws SQLException {
        String sql = "SELECT points FROM \"tournament_points\" WHERE username='"+username+"';";
        Connection conn = ConenctionPool.getConnection();
        Statement statement = conn.createStatement();

        ResultSet resultSet = statement.executeQuery(sql);
        return resultSet.getInt("points");

    }
    public List <UserPoints> getCurrentLeaderboard(Integer tournamentYear) throws SQLException {
        String sql = "SELECT username, SUM(points) as total_points FROM \"match_points\" GROUP BY username ORDER BY total_points DESC;";
        Connection conn = ConenctionPool.getConnection();
        Statement statement = conn.createStatement();

        ResultSet resultSet = statement.executeQuery(sql);
        List<UserPoints> pointsList = new ArrayList<>();

        while (resultSet.next()){
            String username = resultSet.getString("username");
            Integer tournamentPoints = resultSet.getInt("total_points");
            pointsList.add(new UserPoints(username,tournamentPoints));
        }

        Tournament tournament = tournamentRepository.getTournament(tournamentYear);
        if (tournament.isFinished){
            for (UserPoints userPoints : pointsList){
                userPoints.matchPoints += getBonusPoints(userPoints.username);
            }
        }

        conn.close();

        return pointsList;

    }
}
