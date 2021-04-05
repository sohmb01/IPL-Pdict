package com.pdict.iplpredict.services;

import com.pdict.iplpredict.database.LoginSessionRepository;
import com.pdict.iplpredict.entities.UserPoints;
import com.pdict.iplpredict.database.CurrentLeaderboardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Path("/currentLeaderboard")
public class CurrentLeaderboardService {
    CurrentLeaderboardRepository currentLeaderboardRepository = new CurrentLeaderboardRepository();
    LoginSessionRepository loginSessionRepository = new LoginSessionRepository();
    Logger logger = LoggerFactory.getLogger(CurrentLeaderboardRepository.class);

    @GET
    @Path("/getCurrentLeaderboard/{tournamentYear}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCurrentLeaderboard(@HeaderParam("HeaderUsername") String headerUsername, @HeaderParam("AccessToken") String accessToken, @PathParam("tournamentYear") Integer tournamentYear){
        logger.info(Instant.now()+" RECEIVED GET: /getCurrentLeaderboard/"+tournamentYear);

        List <UserPoints> pointsList= new ArrayList<>();
        try {
            String activeToken = loginSessionRepository.getActiveToken(headerUsername);
            if(activeToken==null || !activeToken.contentEquals(accessToken)) {
                logger.info(Instant.now()+" AUTHORIZATION FAILURE GET: /getCurrentLeaderboard/"+tournamentYear);
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }

            pointsList = currentLeaderboardRepository.getCurrentLeaderboard(tournamentYear);
        } catch (SQLException sqlException) {
            logger.error(Instant.now()+" DBOPFAILURE GET: /getCurrentLeaderboard/"+tournamentYear, sqlException);
            return Response.status(500).build();
        }

        logger.info(Instant.now()+" DBOPSUCCESS GET: /getCurrentLeaderboard/"+tournamentYear);

        return Response.ok()
                .entity(pointsList)
                .build();
    }

}
