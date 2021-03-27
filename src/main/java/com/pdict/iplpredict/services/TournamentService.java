package com.pdict.iplpredict.services;

import com.pdict.iplpredict.database.TournamentRepository;
import com.pdict.iplpredict.entities.Tournament;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.sql.SQLException;

@Path("/tournament")
public class TournamentService {
    private TournamentRepository tournamentRepository = new TournamentRepository();

    @GET
    @Path("/getTournament/{tournament_year}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTournament(@PathParam("tournament_year") Integer tournamentYear) throws SQLException {
        Tournament tournament = tournamentRepository.getTournament(tournamentYear);

        return Response.ok()
                .entity(tournament)
                .build();
    }

    @POST
    @Path("/createTournament")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTournament(Tournament tournament) throws SQLException {
        tournamentRepository.addTournament(tournament);
        return Response.status(201).build();
    }

    @PUT
    @Path("/updateTournament")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateTournament(Tournament tournament) throws SQLException{
        tournamentRepository.updateTournament(tournament);
        return Response.status(201).build();
    }

    @DELETE
    @Path("/deleteTournament/{tournament_year}")
    public Response deleteTournament(@PathParam("tournament_year") Integer tournamentYear) throws SQLException {
        tournamentRepository.deleteTournament( tournamentYear);
        return Response.status(200).build();
    }

}
