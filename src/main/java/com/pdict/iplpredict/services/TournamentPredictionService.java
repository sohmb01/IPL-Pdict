package com.pdict.iplpredict.services;

import com.pdict.iplpredict.database.LoginSessionRepository;
import com.pdict.iplpredict.database.TournamentRepository;
import com.pdict.iplpredict.database.TournamentPredictionRepository;
import com.pdict.iplpredict.entities.Tournament;
import com.pdict.iplpredict.entities.TournamentPrediction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.time.LocalDate;
import java.sql.SQLException;
import java.time.Instant;
import java.time.ZoneId;

@Path("/tournamentPrediction")
public class TournamentPredictionService {
    private TournamentPredictionRepository tournamentPredictionRepository = new TournamentPredictionRepository();
    LoginSessionRepository loginSessionRepository = new LoginSessionRepository();
    TournamentRepository tournamentRepository = new TournamentRepository();
    Logger logger = LoggerFactory.getLogger(TournamentPredictionService.class);

    @GET
    @Path("/getTournamentPrediction/{username}/{tournament_year}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTournamentPrediction(@HeaderParam("HeaderUsername") String headerUsername, @HeaderParam("AccessToken") String accessToken, @PathParam("username") String userName ,@PathParam("tournament_year") Integer tournamentYear) {
        logger.info(Instant.now()+" RECEIVED GET: /getTournamentPrediction/"+userName+"/"+tournamentYear);

        TournamentPrediction tournamentPrediction = null;
        try {
            String activeToken = loginSessionRepository.getActiveToken(headerUsername);
            if(activeToken==null || !activeToken.contentEquals(accessToken) || !userName.contentEquals(headerUsername)) {
                logger.info(Instant.now()+" AUTHORIZATION FAILURE GET: /getTournamentPrediction/"+userName+"/"+tournamentYear);
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }

            tournamentPrediction = tournamentPredictionRepository.getTournamentPrediction(userName , tournamentYear);

            if(tournamentPrediction==null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
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
    public Response addTournamentPrediction(@HeaderParam("HeaderUsername") String headerUsername, @HeaderParam("AccessToken") String accessToken, TournamentPrediction tournamentPrediction) {
        logger.info(Instant.now()+" RECEIVED POST: /createTournamentPrediction "+tournamentPrediction);

        try {
            String activeToken = loginSessionRepository.getActiveToken(headerUsername);
            if(activeToken==null || !activeToken.contentEquals(accessToken) || !tournamentPrediction.userName.contentEquals(headerUsername)) {
                logger.info(Instant.now()+" AUTHORIZATION FAILURE POST: /createTournamentPrediction "+tournamentPrediction);
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }

            Tournament tournament = tournamentRepository.getTournament(tournamentPrediction.tournamentYear);
            if(tournament==null) {
                logger.info(Instant.now()+" INVALID REQUEST: tournamentYear doesn't exist POST: /createTournamentPrediction "+tournamentPrediction);
                return Response.status(Response.Status.NOT_ACCEPTABLE).build();
            }

            if(isPredictDeadlinePassed(tournament)) {
                logger.info(Instant.now()+" INVALID REQUEST: Deadline has Passed POST: /createTournamentPrediction "+tournamentPrediction);
                return Response.status(Response.Status.NOT_ACCEPTABLE).build();
            }

            tournamentPredictionRepository.addTournamentPrediction( tournamentPrediction);
        }
        catch (SQLException sqlException) {
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
    public Response updateTournamentPrediction(@HeaderParam("HeaderUsername") String headerUsername, @HeaderParam("AccessToken") String accessToken, TournamentPrediction tournamentPrediction) {
        logger.info(Instant.now()+" RECEIVED PUT: /updateTournamentPrediction "+tournamentPrediction);

        try {
            String activeToken = loginSessionRepository.getActiveToken(headerUsername);
            if(activeToken==null || !activeToken.contentEquals(accessToken) || !tournamentPrediction.userName.contentEquals(headerUsername)) {
                logger.info(Instant.now()+" AUTHORIZATION FAILURE PUT: /updateTournamentPrediction "+tournamentPrediction);
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }

            Tournament tournament = tournamentRepository.getTournament(tournamentPrediction.tournamentYear);
            if(tournament==null) {
                logger.info(Instant.now()+" INVALID REQUEST: tournamentYear doesn't exist PUT: /updateTournamentPrediction "+tournamentPrediction);
                return Response.status(Response.Status.NOT_ACCEPTABLE).build();
            }

            if(isPredictDeadlinePassed(tournament)) {
                logger.info(Instant.now()+" INVALID REQUEST: Deadline has Passed PUT: /updateTournamentPrediction "+tournamentPrediction);
                return Response.status(Response.Status.NOT_ACCEPTABLE).build();
            }

            tournamentPredictionRepository.updateTournamentPrediction(tournamentPrediction);
        }
        catch (SQLException sqlException) {
            logger.error(Instant.now()+" DBOPFAILURE PUT: /updateTournamentPrediction "+tournamentPrediction, sqlException);
            return Response.status(500).build();
        }

        logger.info(Instant.now()+" DBOPSUCCESS PUT: /updateTournamentPrediction "+tournamentPrediction);

        return Response.status(201).build();
    }

    private Boolean isPredictDeadlinePassed(Tournament tournament) throws SQLException {
        LocalDate currentdate = LocalDate.now(ZoneId.of("Asia/Kolkata"));
        LocalDate tournamentStartDate = LocalDate.of(tournament.tournamentStartYear, tournament.tournamentStartMonth, tournament.tournamentStartDay+2);

        return currentdate.isAfter(tournamentStartDate);
    }
}