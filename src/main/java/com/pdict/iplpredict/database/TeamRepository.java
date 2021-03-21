package com.pdict.iplpredict.database;

import com.pdict.iplpredict.entities.Team;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeamRepository {
    DatabaseInteraction databaseInteraction = new DatabaseInteraction();

    public Team getTeamByTeamCode(String teamCode) throws SQLException {
        String sql = "SELECT * FROM \"team\" WHERE team_code='" + teamCode + "';";

        ResultSet resultSet = databaseInteraction.executeQuery(sql);
        resultSet.next();


        String teamName = resultSet.getString("team_name");

        return new Team( teamCode , teamName );
    }

    public void addTeam(Team team) throws SQLException {
        String sql = "INSERT INTO \"team\" (team_code , team_name) VALUES ('"+team.teamCode+"', '"+team.teamName+"');";

        databaseInteraction.executeUpdate(sql);
    }

    public void updateTeam(Team team) throws SQLException {
        String sql = "UPDATE \"team\" SET team_name='"+team.teamName+"' WHERE team_code="+team.teamCode+";";

        databaseInteraction.executeUpdate(sql);
    }

    public void deleteTeam(String teamCode) throws SQLException {
        String sql = "DELETE FROM \"team\" WHERE team_code='"+teamCode+"';";

        databaseInteraction.executeUpdate(sql);
    }
}