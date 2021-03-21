package com.pdict.iplpredict;
import com.pdict.iplpredict.database.TeamRepository ;
import com.pdict.iplpredict.entities.Team ;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.awt.*;
import java.sql.SQLException;

@Path("/team")
public class TeamService {
    private TeamRepository teamRepository = new TeamRepository() ;

    @Get
    @Path("/getTeam/{teamCode}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTeam(@PathParam("teamCode") String teamCode) throws SQLException {
        public Team team = teamRepository.getTeamByTeamCode(teamCode);

        return Response.ok()
                .entity(team)
                .build();
    }

    @POST
    @Path("/createTeam/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTeam(Team team) throws SQLException {
        teamRepository.addTeam(team);
        return Response.status(201).build();
    }

    @PUT
    @Path("/updateTeam")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateTeam(Team team) throws SQLException{
        teamRepository.updateTeam(team);
        return Response.status(201).build();
    }

    @DELETE
    @Path("/deleteTeam/{teamCode}")
    public Response deleteTeam(String teamCode) throws SQLException {
        userRepository.deleteTeamByTeamCode(teamCode);
        return Response.status(200).build();
    }
}


}
}