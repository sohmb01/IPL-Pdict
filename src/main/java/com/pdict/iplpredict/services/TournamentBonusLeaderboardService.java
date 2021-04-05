package com.pdict.iplpredict.services;

import com.pdict.iplpredict.database.CurrentLeaderboardRepository;
import com.pdict.iplpredict.database.LoginSessionRepository;
import com.pdict.iplpredict.database.TournamentBonusLeaderboardRepository;
import com.pdict.iplpredict.entities.UserPoints;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Path("/tournamentBonusLeaderboard")
public class TournamentBonusLeaderboardService {
    TournamentBonusLeaderboardRepository tournamentBonusLeaderboardRepository = new TournamentBonusLeaderboardRepository();
    LoginSessionRepository loginSessionRepository = new LoginSessionRepository();
    Logger logger = LoggerFactory.getLogger(TournamentBonusLeaderboardRepository.class);

    @GET
    @Path("/getTournamentBonusLeaderboard/{tournamentYear}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTournamentBonusLeaderboard(@HeaderParam("HeaderUsername") String headerUsername, @HeaderParam("AccessToken") String accessToken, @PathParam("tournamentYear") Integer tournamentYear) {
        logger.info(Instant.now() + " RECEIVED GET: /getTournamentBonusLeaderboard/" + tournamentYear);

        List<UserPoints> pointsList = new ArrayList<>();
        try {
            String activeToken = loginSessionRepository.getActiveToken(headerUsername);
            if(activeToken==null || !activeToken.contentEquals(accessToken)) {
                logger.info(Instant.now() + " AUTHORIZATION FAILURE GET: /getTournamentBonusLeaderboard/" + tournamentYear);
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }

            pointsList = tournamentBonusLeaderboardRepository.getTournamentBonusLeaderboard(tournamentYear);
        } catch (SQLException sqlException) {
            logger.error(Instant.now() + " DBOPFAILURE GET: /getTournamentBonusLeaderboard/" + tournamentYear, sqlException);
            return Response.status(500).build();
        }

        logger.info(Instant.now() + " DBOPSUCCESS GET: /getTournamentBonusLeaderboard/" + tournamentYear);

        return Response.ok()
                .entity(pointsList)
                .build();
    }
}
