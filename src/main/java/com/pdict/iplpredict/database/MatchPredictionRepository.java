package com.pdict.iplpredict.database;

import com.pdict.iplpredict.entities.MatchPrediction;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MatchPredictionRepository {

    public MatchPrediction getMatchPredictionByMatchIdAndUsername(String username, String matchId) throws SQLException {
        String sql = "SELECT * FROM \"match_prediction\" WHERE username='"+username+"' AND match_id='"+matchId+"';";

        Connection conn = ConenctionPool.getConnection();
        Statement statement = conn.createStatement();

        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();

        String teamWin = resultSet.getString("team_win");
        Integer teamHigh1 = resultSet.getInt("team1_high");
        Integer teamLow1 = resultSet.getInt("team1_low");
        Integer teamHigh2 = resultSet.getInt("team2_high");
        Integer teamLow2 = resultSet.getInt("team2_low");
        Integer wickets = resultSet.getInt("wickets");

        conn.close();

        return new MatchPrediction(username, matchId, teamWin, teamHigh1, teamLow1, teamHigh2, teamLow2, wickets);
    }


    public void insertMatchPrediction(MatchPrediction matchPrediction) throws SQLException {
        String sql = "INSERT INTO \"match_prediction\" VALUES ('"+ matchPrediction.username+"', "+ matchPrediction.matchId+", '"+ matchPrediction.teamWin+"', "+ matchPrediction.teamHigh1+", "+ matchPrediction.teamLow1+", "+ matchPrediction.teamHigh2+", "+ matchPrediction.teamLow2+","+ matchPrediction.wickets+")";

        Connection conn = ConenctionPool.getConnection();
        Statement statement = conn.createStatement();

        statement.executeUpdate(sql);

        conn.close();
    }


    public void updateMatchPrediction(MatchPrediction matchPrediction) throws SQLException {
        String sql = "UPDATE \"match_prediction\" SET username='"+ matchPrediction.username+"', match_id="+ matchPrediction.matchId+", team_win='"+ matchPrediction.teamWin+"', team1_high="+ matchPrediction.teamHigh1+", team1_low="+ matchPrediction.teamLow1+", team2_high="+ matchPrediction.teamHigh2+", team2_low="+ matchPrediction.teamLow2+", wickets="+ matchPrediction.wickets+" WHERE match_id="+ matchPrediction.matchId+" AND username='"+ matchPrediction.username+"';";

        Connection conn = ConenctionPool.getConnection();
        Statement statement = conn.createStatement();

        statement.executeUpdate(sql);

        conn.close();
    }
}
