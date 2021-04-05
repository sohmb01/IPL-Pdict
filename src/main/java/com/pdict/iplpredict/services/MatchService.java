package com.pdict.iplpredict.services;

import com.pdict.iplpredict.database.MatchRepository;
import com.pdict.iplpredict.entities.Match;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.time.Instant;
import java.util.List;

@Path("/match")
public class MatchService {
    private MatchRepository matchRepository = new MatchRepository();
    Logger logger = LoggerFactory.getLogger(MatchService.class);

    @GET
    @Path("/getMatch/{matchId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMatch(@PathParam("matchId") String matchId)  {
        logger.info(Instant.now()+" RECEIVED GET: /getMatch/"+matchId);

        Match match = null;
        try {
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
    public Response getAllMatches()  {
        logger.info(Instant.now()+" RECEIVED GET: /getAllMatches");

        List<Match> matches = null;
        try {
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

