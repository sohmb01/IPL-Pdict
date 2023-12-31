package com.pdict.iplpredict.services;

import com.pdict.iplpredict.database.LoginSessionRepository;
import com.pdict.iplpredict.database.MatchLeaderboardRepository;
import com.pdict.iplpredict.entities.UserPoints;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.time.Instant;
import java.util.List;

@Path("/matchLeaderboard")
public class MatchLeaderboardService {
    MatchLeaderboardRepository matchLeaderboardRepository = new MatchLeaderboardRepository();
    LoginSessionRepository loginSessionRepository = new LoginSessionRepository();
    Logger logger = LoggerFactory.getLogger(MatchLeaderboardRepository.class);

    @GET
    @Path("/getMatchLeaderboard/{matchId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMatchLeaderboard(@HeaderParam("HeaderUsername") String headerUsername, @HeaderParam("AccessToken") String accessToken, @PathParam("matchId") String matchId){
        logger.info(Instant.now()+" RECEIVED GET: /getMatchLeaderboard/"+matchId);

        List<UserPoints> pointsList = null;
        try {
            String activeToken = loginSessionRepository.getActiveToken(headerUsername);
            if(activeToken==null || !activeToken.contentEquals(accessToken)) {
                logger.info(Instant.now()+" AUTHORIZATION FAILURE GET: /getMatchLeaderboard/"+matchId);
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }

            pointsList = matchLeaderboardRepository.getMatchLeaderboard(matchId);
        }
        catch (SQLException sqlException) {
            logger.error(Instant.now()+" DBOPFAILURE GET: /getMatchLeaderboard/"+matchId, sqlException);
            return Response.status(500).build();
        }

        logger.info(Instant.now()+" DBOPSUCCESS GET: /getMatchLeaderboard/"+matchId);

        return Response.ok()
                .entity(pointsList)
                .build();
    }
}
