
package com.pdict.iplpredict.database;

        import com.pdict.iplpredict.entities.Match;
        import com.pdict.iplpredict.entities.MatchwisePrediction;
        import com.pdict.iplpredict.entities.User;

        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.util.ArrayList;
        import java.util.List;

public class MatchwisePredictionRepository {
    DatabaseInteraction databaseInteraction = new DatabaseInteraction();

    public MatchwisePrediction getMatchwisePredictionByMatchIdAndUsername(String username, Integer matchId) throws SQLException {
        String sql = "SELECT * FROM \"matchwise_prediction\" WHERE username='"+username+"' matchId='"+matchId+"';";

        ResultSet resultSet = databaseInteraction.executeQuery(sql);
        resultSet.next();

         String teamWin = resultSet.getString("team_win");
         Integer teamHigh1 = resultSet.getInt("team1_high");
         Integer teamLow1 = resultSet.getInt("team1_low");
         Integer teamHigh2 = resultSet.getInt("team2_high");
         Integer teamLow2 = resultSet.getInt("team2_low");
         Integer wickets = resultSet.getInt("wickets");

        return new MatchwisePrediction(username, matchId, teamWin, teamHigh1, teamLow1, teamHigh2, teamLow2, wickets);
    }


    public void insertMatchwisePrediction(MatchwisePrediction matchwisePrediction) throws SQLException {
        String sql = "INSERT INTO \"matchwise_prediction\" VALUES ('"+matchwisePrediction.username+"', '"+matchwisePrediction.matchId+"', '"+matchwisePrediction.teamWin+"', '"+matchwisePrediction.teamHigh1+", '"+matchwisePrediction.teamLow1+", '"+matchwisePrediction.teamHigh2+"', '"+matchwisePrediction.teamLow2+"','"+matchwisePrediction.wickets+"')";

        databaseInteraction.executeUpdate(sql);
    }


    public void updateMatchwisePrediction(MatchwisePrediction matchwisePrediction) throws SQLException {
        String sql = "UPDATE \"matchwise_prediction\" SET username='"+matchwisePrediction.username+"', match_id='"+matchwisePrediction.matchId+"', team_win='"+matchwisePrediction.teamWin+"', team1_high='"+matchwisePrediction.teamHigh1+"', team1_low='"+matchwisePrediction.teamLow1+"', team2_high='"+matchwisePrediction.teamHigh2+"', team2_low='"+matchwisePrediction.teamLow2+"', wickets='"+matchwisePrediction.wickets+"' WHERE match_id="+matchwisePrediction.matchId+" AND username="+matchwisePrediction.username+";";

        databaseInteraction.executeUpdate(sql);
    }


}
