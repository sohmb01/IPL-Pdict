package com.pdict.iplpredict.services;

import com.pdict.iplpredict.database.TournamentPredictionRepository;
import com.pdict.iplpredict.entities.TournamentPrediction;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.sql.SQLException;

@Path("/tournamentPrediction")
public class TournamentPredictionService {
    private TournamentPredictionRepository tournamentPredictionRepository = new TournamentPredictionRepository();

    @GET
    @Path("/getTournamentPrediction/{username}/{tournament_year}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTournamentPrediction(@PathParam("username") String userName ,@PathParam("tournament_year") Integer tournamentYear) throws SQLException {
        TournamentPrediction tournamentPrediction = tournamentPredictionRepository.getTournamentPrediction(userName , tournamentYear);

        return Response.ok()
                .entity(tournamentPrediction)
                .build();
    }

    @POST
    @Path("/createTournamentPrediction/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTournamentPrediction(TournamentPrediction tournamentPrediction) throws SQLException {
        tournamentPredictionRepository.addTournamentPrediction( tournamentPrediction);
        return Response.status(201).build();
    }

    @PUT
    @Path("/updateTournamentPrediction")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateTournamentPrediction(TournamentPrediction tournamentPrediction) throws SQLException{
        tournamentPredictionRepository.updateTournamentPrediction(tournamentPrediction);
        return Response.status(201).build();
    }
}