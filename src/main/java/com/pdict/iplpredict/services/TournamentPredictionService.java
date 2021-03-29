package com.pdict.iplpredict.services;

import com.pdict.iplpredict.database.TournamentPredictionRepository;
import com.pdict.iplpredict.entities.TournamentPrediction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.sql.SQLException;
import java.time.Instant;

@Path("/tournamentPrediction")
public class TournamentPredictionService {
    private TournamentPredictionRepository tournamentPredictionRepository = new TournamentPredictionRepository();
    Logger logger = LoggerFactory.getLogger(TournamentPredictionService.class);

    @GET
    @Path("/getTournamentPrediction/{username}/{tournament_year}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTournamentPrediction(@PathParam("username") String userName ,@PathParam("tournament_year") Integer tournamentYear) {
        logger.info(Instant.now()+" RECEIVED GET: /getTournamentPrediction/"+userName+"/"+tournamentYear);

        TournamentPrediction tournamentPrediction = null;
        try {
            tournamentPrediction = tournamentPredictionRepository.getTournamentPrediction(userName , tournamentYear);
        } catch (SQLException sqlException) {
            logger.error(Instant.now()+" DBOPFAILURE GET: /getTournamentPrediction/"+userName+"/"+tournamentYear, sqlException);
            return Response.status(500).build();
        }

        logger.info(Instant.now()+" DBOPSUCCESS GET: /getTournamentPrediction/"+userName+"/"+tournamentYear);

        return Response.ok()
                .entity(tournamentPrediction)
                .build();
    }

    @POST
    @Path("/createTournamentPrediction/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTournamentPrediction(TournamentPrediction tournamentPrediction) {
        logger.info(Instant.now()+" RECEIVED POST: /createTournamentPrediction "+tournamentPrediction);

        try {
            tournamentPredictionRepository.addTournamentPrediction( tournamentPrediction);
        } catch (SQLException sqlException) {
            logger.error(Instant.now()+" DBOPFAILURE POST: /createTournamentPrediction "+tournamentPrediction, sqlException);
            return Response.status(500).build();
        }

        logger.info(Instant.now()+" DBOPSUCCESS POST: /createTournamentPrediction "+tournamentPrediction);

        return Response.status(201).build();
    }

    @PUT
    @Path("/updateTournamentPrediction")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateTournamentPrediction(TournamentPrediction tournamentPrediction) {
        logger.info(Instant.now()+" RECEIVED PUT: /updateTournamentPrediction "+tournamentPrediction);

        try {
            tournamentPredictionRepository.updateTournamentPrediction(tournamentPrediction);
        } catch (SQLException sqlException) {
            logger.error(Instant.now()+" DBOPFAILURE PUT: /updateTournamentPrediction "+tournamentPrediction, sqlException);
            return Response.status(500).build();
        }

        logger.info(Instant.now()+" DBOPSUCCESS PUT: /updateTournamentPrediction "+tournamentPrediction);

        return Response.status(201).build();
    }
}