package com.pdict.iplpredict.services;
import com.pdict.iplpredict.database.LoginSessionRepository;
import com.pdict.iplpredict.database.TeamRepository ;
import com.pdict.iplpredict.entities.Team ;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.sql.SQLException;
import java.time.Instant;

@Path("/team")
public class TeamService {
    private TeamRepository teamRepository = new TeamRepository() ;
    LoginSessionRepository loginSessionRepository = new LoginSessionRepository();
    Logger logger = LoggerFactory.getLogger(TeamService.class);

    @GET
    @Path("/getTeam/{teamCode}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTeam(@HeaderParam("HeaderUsername") String headerUsername, @HeaderParam("AccessToken") String accessToken, @PathParam("teamCode") String teamCode) {
        logger.info(Instant.now()+" RECEIVED GET: /getTeam/"+teamCode);

        Team team = null;
        try {
            String activeToken = loginSessionRepository.getActiveToken(headerUsername);
            if(activeToken==null || !activeToken.contentEquals(accessToken)) {
                logger.info(Instant.now()+" AUTHORIZATION FAILURE GET: /getTeam/"+teamCode);
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }

            team = teamRepository.getTeamByTeamCode(teamCode);

            if(team==null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (SQLException sqlException) {
            logger.error(Instant.now()+" DBOPFAILURE GET: /getTeam/"+teamCode, sqlException);

            return Response.status(500).build();
        }

        logger.info(Instant.now()+" DBOPSUCCESS GET: /getTeam/"+teamCode);

        return Response.ok()
                .entity(team)
                .build();
    }

//    @POST
//    @Path("/createTeam/")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response addTeam(Team team) {
//        logger.info(Instant.now()+" RECEIVED POST: /createTeam "+team);
//
//        try {
//            teamRepository.addTeam(team);
//        } catch (SQLException sqlException) {
//            logger.error(Instant.now()+" DBOPFAILURE POST: /createTeam "+team, sqlException);
//
//            return Response.status(500).build();
//        }
//
//        logger.info(Instant.now()+" DBOPSUCCESS POST: /createTeam "+team);
//
//        return Response.status(201).build();
//    }

//    @PUT
//    @Path("/updateTeam")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response updateTeam(Team team) {
//        logger.info(Instant.now()+" RECEIVED PUT: /updateTeam "+team);
//
//        try {
//            teamRepository.updateTeam(team);
//        } catch (SQLException sqlException) {
//            logger.error(Instant.now()+" DBOPFAILURE PUT: /updateTeam "+team, sqlException);
//
//            return Response.status(500).build();
//        }
//
//        logger.info(Instant.now()+" DBOPSUCCESS PUT: /updateTeam "+team);
//
//        return Response.status(201).build();
//    }

//    @DELETE
//    @Path("/deleteTeam/{teamCode}")
//    public Response deleteTeam(@PathParam("teamCode") String teamCode) {
//        logger.info("RECEIVED DELETE: /deleteTeam/"+teamCode);
//
//        try {
//            teamRepository.deleteTeam(teamCode);
//        } catch (SQLException sqlException) {
//            logger.error(Instant.now()+" DBOPFAILURE DELETE: /deleteTeam/"+teamCode, sqlException);
//
//            return Response.status(500).build();
//        }
//
//        logger.info(Instant.now()+" DBOPSUCCESS DELETE: /deleteTeam/"+teamCode);
//
//        return Response.status(200).build();
//    }
}