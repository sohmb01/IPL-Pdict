package com.pdict.iplpredict;

import com.pdict.iplpredict.entities.TournamentResult;
import com.pdict.iplpredict.database.TournamentResultRepository;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.sql.SQLException;

public class TournamentResultService {
    private TournamentResultRepository tournamentResultRepository = new TournamentResultRepository();

    @GET
    @Path("/getTournamentResult/{tournament_year}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTournamentResult(@PathParam("tournament_year") Integer tournamentYear) throws SQLException {
        TournamentResult tournamentResult = tournamentResultRepository.getTournamentResult(tournamentYear);

        return Response.ok()
                .entity(tournamentResult)
                .build();
    }

    @POST
    @Path("/addTournamentResult/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTournamentResult(TournamentResult tournamentResult) throws SQLException {
        tournamentResultRepository.addTournamentResult(tournamentResult);
        return Response.status(201).build();
    }

    @PUT
    @Path("/updateTournamentResult")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateTournamentResult(TournamentResult tournamentResult) throws SQLException{
        tournamentResultRepository.updateTournamentResult(tournamentResult);
        return Response.status(201).build();
    }

    @DELETE
    @Path("/deleteTournamentResult/{tournament_year}")
    public Response deleteTournamentResult(@PathParam("tournament_year") Integer tournamentYear) throws SQLException {
        tournamentResultRepository.deleteTournamentResult( tournamentYear);
        return Response.status(200).build();
    }

}
