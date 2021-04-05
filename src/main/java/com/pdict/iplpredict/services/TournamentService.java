package com.pdict.iplpredict.services;

import com.pdict.iplpredict.database.TournamentRepository;
import com.pdict.iplpredict.entities.Tournament;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.sql.SQLException;
import java.time.Instant;

@Path("/tournament")
public class TournamentService {
    private TournamentRepository tournamentRepository = new TournamentRepository();
    Logger logger = LoggerFactory.getLogger(TournamentService.class);

    @GET
    @Path("/getTournament/{tournament_year}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTournament(@PathParam("tournament_year") Integer tournamentYear){
        logger.info(Instant.now()+" RECEIVED GET: /getTournament/"+tournamentYear);
        
        Tournament tournament = null;
        try {
            tournament = tournamentRepository.getTournament(tournamentYear);
        } catch (SQLException sqlException) {
            logger.error(Instant.now()+" DBOPFAILURE GET: /getTournament/"+tournamentYear, sqlException);
            return Response.status(500).build();
        }

        logger.info(Instant.now()+" DBOPSUCCESS GET: /getTournament/"+tournamentYear);

        return Response.ok()
                .entity(tournament)
                .build();
    }

//    @POST
//    @Path("/createTournament")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response addTournament(Tournament tournament) {
//        logger.info(Instant.now()+" RECEIVED POST: /createTournament "+tournament);
//
//        try {
//            tournamentRepository.addTournament(tournament);
//        } catch (SQLException sqlException) {
//            logger.error(Instant.now()+" DBOPFAILURE POST: /createTournament "+tournament, sqlException);
//            return Response.status(500).build();
//        }
//
//        logger.info(Instant.now()+" DBOPSUCCESS POST: /createTournament "+tournament);
//
//        return Response.status(201).build();
//    }

//    @PUT
//    @Path("/updateTournament")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response updateTournament(Tournament tournament) {
//        logger.info(Instant.now()+" RECEIVED PUT: /updateTournament "+tournament);
//
//        try {
//            tournamentRepository.updateTournament(tournament);
//        } catch (SQLException sqlException) {
//            logger.error(Instant.now()+" DBOPFAILURE PUT: /updateTournament "+tournament, sqlException);
//            return Response.status(500).build();
//        }
//
//        logger.info(Instant.now()+" DBOPSUCCESS PUT: /updateTournament "+tournament);
//
//        return Response.status(201).build();
//    }

//    @DELETE
//    @Path("/deleteTournament/{tournament_year}")
//    public Response deleteTournament(@PathParam("tournament_year") Integer tournamentYear) {
//        logger.info("RECEIVED DELETE: /deleteTournament/"+tournamentYear);
//
//        try {
//            tournamentRepository.deleteTournament( tournamentYear);
//        } catch (SQLException sqlException) {
//            logger.error(Instant.now()+" DBOPFAILURE DELETE: /deleteTournament/"+tournamentYear, sqlException);
//            return Response.status(500).build();
//        }
//
//        logger.info(Instant.now()+" DBOPSUCCESS DELETE: /deleteTournament/"+tournamentYear);
//
//        return Response.status(200).build();
//    }

}
