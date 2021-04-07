package com.pdict.iplpredict.services;

import com.pdict.iplpredict.database.LoginSessionRepository;
import com.pdict.iplpredict.entities.Match;
import com.pdict.iplpredict.database.MatchPredictionRepository;
import com.pdict.iplpredict.database.MatchRepository;
import com.pdict.iplpredict.entities.MatchPrediction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Path("/matchPrediction")
public class MatchPredictionService {
    private MatchPredictionRepository matchPredictionRepository = new MatchPredictionRepository();
    LoginSessionRepository loginSessionRepository = new LoginSessionRepository();
    MatchRepository matchRepository = new MatchRepository();
    Logger logger = LoggerFactory.getLogger(MatchPredictionService.class);

    @GET
    @Path("/getMatchPrediction/{username}/{matchId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMatchPrediction(@HeaderParam("HeaderUsername") String headerUsername, @HeaderParam("AccessToken") String accessToken, @PathParam("username") String username, @PathParam("matchId") String matchId) {
        logger.info(Instant.now()+" RECEIVED GET: /getMatchPrediction/"+username+"/"+matchId);

        MatchPrediction matchPrediction = null;
        try {
            String activeToken = loginSessionRepository.getActiveToken(headerUsername);
            if(activeToken==null || !activeToken.contentEquals(accessToken) || !username.contentEquals(headerUsername)) {
                logger.info(Instant.now()+" AUTHORIZATION FAILURE GET: /getMatchPrediction/"+username+"/"+matchId);
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }

            matchPrediction = matchPredictionRepository.getMatchPredictionByMatchIdAndUsername(username, matchId);

            if(matchPrediction==null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (SQLException sqlException) {
            logger.error(Instant.now()+" DBOPFAILURE GET: /getMatchPrediction/"+username+"/"+matchId, sqlException);
            return Response.status(500).build();
        }

        logger.info(Instant.now()+" DBOPSUCCESS GET: /getMatchPrediction/"+username+"/"+matchId);

        return Response.ok()
                .entity(matchPrediction)
                .build();
    }

    @POST
    @Path("/createMatchPrediction/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createMatchPrediction(@HeaderParam("HeaderUsername") String headerUsername, @HeaderParam("AccessToken") String accessToken, MatchPrediction matchPrediction) {
        logger.info(Instant.now()+" RECEIVED POST: /createMatchPrediction "+matchPrediction);

        try {
            String activeToken = loginSessionRepository.getActiveToken(headerUsername);
            if(activeToken==null || !activeToken.contentEquals(accessToken) || !matchPrediction.userName.contentEquals(headerUsername)) {
                logger.info(Instant.now()+" AUTHORIZATION FAILURE POST: /createMatchPrediction "+matchPrediction);
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }

            Match match = matchRepository.getMatchByMatchId(matchPrediction.matchId);
            if(match==null) {
                logger.info(Instant.now()+" INVALID REQUEST: MatchId doesnt exist POST: /createMatchPrediction "+matchPrediction);
                return Response.status(Response.Status.NOT_ACCEPTABLE).build();
            }

            if(isPredictDeadlinePassed(match)) {
                logger.info(Instant.now()+" INVALID REQUEST: Deadline has Passed POST: /createMatchPrediction "+matchPrediction);
                return Response.status(Response.Status.NOT_ACCEPTABLE).build();
            }

            matchPredictionRepository.insertMatchPrediction(matchPrediction);
        }
        catch (SQLException sqlException) {
            logger.error(Instant.now()+" DBOPFAILURE POST: /createMatchPrediction "+matchPrediction, sqlException);
            return Response.status(500).build();
        }

        logger.info(Instant.now()+" DBOPSUCCESS POST: /createMatchPrediction "+matchPrediction);

        return Response.status(201).build();
    }

    @PUT
    @Path("/updateMatchPrediction")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateMatchPrediction(@HeaderParam("HeaderUsername") String headerUsername, @HeaderParam("AccessToken") String accessToken, MatchPrediction matchPrediction) {
        logger.info(Instant.now()+" RECEIVED PUT: /updateMatchPrediction "+matchPrediction);

        try {
            String activeToken = loginSessionRepository.getActiveToken(headerUsername);
            if(activeToken==null || !activeToken.contentEquals(accessToken) || !matchPrediction.userName.contentEquals(headerUsername)) {
                logger.info(Instant.now()+" AUTHORIZATION FAILURE PUT: /updateMatchPrediction "+matchPrediction);
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }

            Match match = matchRepository.getMatchByMatchId(matchPrediction.matchId);
            if(match==null) {
                logger.info(Instant.now()+" INVALID REQUEST: MatchId doesnt exist PUT: /updateMatchPrediction "+matchPrediction);
                return Response.status(Response.Status.NOT_ACCEPTABLE).build();
            }

            if(isPredictDeadlinePassed(match)) {
                logger.info(Instant.now()+" INVALID REQUEST: Deadline has Passed PUT: /updateMatchPrediction "+matchPrediction);
                return Response.status(Response.Status.NOT_ACCEPTABLE).build();
            }

            matchPredictionRepository.updateMatchPrediction(matchPrediction);
        }
        catch (SQLException sqlException) {
            logger.error(Instant.now()+" DBOPFAILURE PUT: /updateMatchPrediction "+matchPrediction, sqlException);
            return Response.status(500).build();
        }

        logger.info(Instant.now()+" DBOPSUCCESS PUT: /updateMatchPrediction "+matchPrediction);

        return Response.status(201).build();
    }

    private Boolean isPredictDeadlinePassed(Match match) throws SQLException {
        LocalDateTime currentDateTime = LocalDateTime.now(ZoneId.of("Asia/Kolkata"));
        LocalDateTime matchStartDateTime = LocalDateTime.of(match.matchStartYear, match.matchStartMonth, match.matchStartDay, match.matchStartHour, match.matchStartMinute);

        return currentDateTime.isAfter(matchStartDateTime);
    }
}

