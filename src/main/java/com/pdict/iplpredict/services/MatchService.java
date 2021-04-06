package com.pdict.iplpredict.services;

import com.pdict.iplpredict.database.LoginSessionRepository;
import com.pdict.iplpredict.database.MatchRepository;
import com.pdict.iplpredict.entities.Match;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Path("/match")
public class MatchService {
    private MatchRepository matchRepository = new MatchRepository();
    LoginSessionRepository loginSessionRepository = new LoginSessionRepository();
    Logger logger = LoggerFactory.getLogger(MatchService.class);

    @GET
    @Path("/getMatch/{matchId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMatch(@HeaderParam("HeaderUsername") String headerUsername, @HeaderParam("AccessToken") String accessToken, @PathParam("matchId") String matchId)  {
        logger.info(Instant.now()+" RECEIVED GET: /getMatch/"+matchId);

        Match match = null;
        try {
            String activeToken = loginSessionRepository.getActiveToken(headerUsername);
            if(activeToken==null || !activeToken.contentEquals(accessToken)) {
                logger.info(Instant.now()+" AUTHORIZATION FAILURE GET: /getMatch/"+matchId);
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }

            match = matchRepository.getMatchByMatchId(matchId);
        } catch (SQLException sqlException) {
            logger.error(Instant.now()+" DBOPFAILURE GET: /getMatch/"+matchId, sqlException);
            return Response.status(500).build();
        }

        logger.info(Instant.now()+" DBOPSUCCESS GET: /getMatch/"+matchId);

        return Response.ok()
                .entity(match)
                .build();
    }

    @GET
    @Path("/getAllMatches")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllMatches(@HeaderParam("HeaderUsername") String headerUsername, @HeaderParam("AccessToken") String accessToken)  {
        logger.info(Instant.now()+" RECEIVED GET: /getAllMatches");

        List<Match> matches = null;
        try {
            String activeToken = loginSessionRepository.getActiveToken(headerUsername);
            if(activeToken==null || !activeToken.contentEquals(accessToken)) {
                logger.info(Instant.now()+" AUTHORIZATION FAILURE GET: /getAllMatches");
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }

            matches = matchRepository.getAllMatches();
        } catch (SQLException sqlException) {
            logger.error(Instant.now()+" DBOPFAILURE GET: /getAllMatches", sqlException);
            return Response.status(500).build();
        }

        logger.info(Instant.now()+" DBOPSUCCESS GET: /getAllMatches");

        return Response.ok()
                .entity(matches)
                .build();
    }

    @GET
    @Path("/getAllActiveMatches")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllActiveMatches(@HeaderParam("HeaderUsername") String headerUsername, @HeaderParam("AccessToken") String accessToken)  {
        logger.info(Instant.now()+" RECEIVED GET: /getAllActiveMatches");

        List<Match> activeMatches = new ArrayList<>();
        try {
            String activeToken = loginSessionRepository.getActiveToken(headerUsername);
            if(activeToken==null || !activeToken.contentEquals(accessToken)) {
                logger.info(Instant.now()+" AUTHORIZATION FAILURE GET: /getAllActiveMatches");
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }

            List<Match> matches = matchRepository.getAllMatches();
            for(Match match : matches) {
                if(isPredictionPossibleForMatch(match)) {
                    activeMatches.add(match);
                }
            }
        } catch (SQLException sqlException) {
            logger.error(Instant.now()+" DBOPFAILURE GET: /getAllActiveMatches", sqlException);
            return Response.status(500).build();
        }

        logger.info(Instant.now()+" DBOPSUCCESS GET: /getAllActiveMatches");

        return Response.ok()
                .entity(activeMatches)
                .build();
    }

    private Boolean isPredictionPossibleForMatch(Match match) {
        String matchStartTime = match.matchDate+" "+match.matchStartTime;
        Timestamp matchStartTimestamp = Timestamp.valueOf(matchStartTime) ;

        return !Timestamp.from(Instant.now()).after(matchStartTimestamp);
    }

//    @POST
//    @Path("/createMatch")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response createMatch(Match match)  {
//        logger.info(Instant.now()+" RECEIVED POST: /createMatch "+match);
//
//        try {
//            matchRepository.insertMatch(match);
//        } catch (SQLException sqlException) {
//            logger.error(Instant.now()+" DBOPFAILURE POST: /createMatch "+match, sqlException);
//            return Response.status(500).build();
//        }
//
//        logger.info(Instant.now()+" DBOPSUCCESS POST: /createMatch "+match);
//
//        return Response.status(201).build();
//    }

//    @PUT
//    @Path("/updateMatch")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response updateMatch(Match match) {
//        logger.info(Instant.now()+" RECEIVED PUT: /updateMatch "+match);
//
//        try {
//            matchRepository.updateMatch(match);
//        } catch (SQLException sqlException) {
//            logger.error(Instant.now()+" DBOPFAILURE PUT: /updateMatch "+match, sqlException);
//            return Response.status(500).build();
//        }
//
//        logger.info(Instant.now()+" DBOPSUCCESS PUT: /updateMatch "+match);
//
//        return Response.status(201).build();
//    }
}

