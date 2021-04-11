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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
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

            if(match==null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
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
                if(isPredictionPossibleForMatch(match) && isTodaysMatch(match)) {
                    activeMatches.add(match);
                }
            }
        } catch (SQLException sqlException) {
            logger.error(Instant.now()+" DBOPFAILURE GET: /getAllActiveMatches", sqlException);
            return Response.status(500).build();
        }

        logger.info(Instant.now()+" DBOPSUCCESS GET: /getAllActiveMatches");

        activeMatches.sort((a,b)-> {
            return LocalDateTime.of(a.matchStartYear, a.matchStartMonth, a.matchStartDay, a.matchStartHour, a.matchStartMinute)
                    .isBefore(LocalDateTime.of(b.matchStartYear, b.matchStartMonth, b.matchStartDay, b.matchStartHour, b.matchStartMinute)) ? -1 : 1;
        });

        if(activeMatches.size()>2) {
            activeMatches = activeMatches.subList(0,2);
        }

        return Response.ok()
                .entity(activeMatches)
                .build();
    }

    private Boolean isPredictionPossibleForMatch(Match match) {
        LocalDateTime currentDateTime = LocalDateTime.now(ZoneId.of("Asia/Kolkata"));
        LocalDateTime matchStartDateTime = LocalDateTime.of(match.matchStartYear, match.matchStartMonth, match.matchStartDay, match.matchStartHour, match.matchStartMinute);

        return currentDateTime.isBefore(matchStartDateTime);
    }

    private Boolean isTodaysMatch(Match match) {
        LocalDate today = LocalDate.now(ZoneId.of("Asia/Kolkata"));
        LocalDate matchDate = LocalDate.of(match.matchStartYear, match.matchStartMonth, match.matchStartDay);

        return today.isEqual(matchDate);
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

