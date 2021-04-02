package com.pdict.iplpredict.services;

import com.pdict.iplpredict.database.MatchPredictionRepository;
import com.pdict.iplpredict.entities.MatchPrediction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.time.Instant;

@Path("/matchPrediction")
public class MatchPredictionService {
    private MatchPredictionRepository matchPredictionRepository = new MatchPredictionRepository();
    Logger logger = LoggerFactory.getLogger(MatchPredictionService.class);

    @GET
    @Path("/getMatchPrediction/{username}/{matchId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMatchPrediction(@PathParam("username") String username, @PathParam("matchId") String matchId) {
        logger.info(Instant.now()+" RECEIVED GET: /getMatchPrediction/"+username+"/"+matchId);

        MatchPrediction matchPrediction = null;
        try {
            matchPrediction = matchPredictionRepository.getMatchPredictionByMatchIdAndUsername(username, matchId);
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
    public Response createMatchPrediction(MatchPrediction matchPrediction) {
        logger.info(Instant.now()+" RECEIVED POST: /createMatchPrediction "+matchPrediction);

        try {
            matchPredictionRepository.insertMatchPrediction(matchPrediction);
        } catch (SQLException sqlException) {
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
    public Response updateMatchPrediction(MatchPrediction matchPrediction) {
        logger.info(Instant.now()+" RECEIVED PUT: /updateMatchPrediction "+matchPrediction);

        try {
            matchPredictionRepository.updateMatchPrediction(matchPrediction);
        } catch (SQLException sqlException) {
            logger.error(Instant.now()+" DBOPFAILURE PUT: /updateMatchPrediction "+matchPrediction, sqlException);
            return Response.status(500).build();
        }

        logger.info(Instant.now()+" DBOPSUCCESS PUT: /updateMatchPrediction "+matchPrediction);

        return Response.status(201).build();
    }
}

