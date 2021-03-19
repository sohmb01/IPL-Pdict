package com.pdict.iplpredict;

import com.pdict.iplpredict.database.*;
import com.pdict.iplpredict.entities.*;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.sql.SQLException;
import java.util.List;

@Path("/match")
public class MatchService {
    private MatchRepository matchRepository = new MatchRepository();

    @GET
    @Path("/getMatch/{matchId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMatch(@PathParam("matchId") Integer matchId) throws SQLException {
        Match match = matchRepository.getMatchByMatchId(matchId);

        return Response.ok()
                .entity(match)
                .build();
    }

    @GET
    @Path("/getAllMatches")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllMatches() throws SQLException {
        List<Match> matches = matchRepository.getAllMatches();

        return Response.ok()
                .entity(matches)
                .build();
    }

    @POST
    @Path("/createMatch/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createMatch(Match match) throws SQLException {
        matchRepository.insertMatch(match);
        return Response.status(201).build();
    }

    @PUT
    @Path("/updateMatch")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateMatch(Match match) throws SQLException{
        matchRepository.updateMatch(match);
        return Response.status(201).build();
    }


}

