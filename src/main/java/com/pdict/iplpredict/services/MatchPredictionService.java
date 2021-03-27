package com.pdict.iplpredict.services;

import com.pdict.iplpredict.database.MatchPredictionRepository;
import com.pdict.iplpredict.entities.MatchPrediction;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("/matchPrediction")
public class MatchPredictionService {
    private MatchPredictionRepository matchPredictionRepository = new MatchPredictionRepository();

    @GET
    @Path("/getMatchPrediction/{username}/{matchId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMatchPrediction(@PathParam("username") String username, @PathParam("matchId") Integer matchId) throws SQLException {
        MatchPrediction matchPrediction = matchPredictionRepository.getMatchPredictionByMatchIdAndUsername(username, matchId);

        return Response.ok()
                .entity(matchPrediction)
                .build();
    }

    @POST
    @Path("/createMatchPrediction/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createMatchPrediction(MatchPrediction matchPrediction) throws SQLException {
        matchPredictionRepository.insertMatchPrediction(matchPrediction);
        return Response.status(201).build();
    }

    @PUT
    @Path("/updateMatchPrediction")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateMatchPrediction(MatchPrediction matchPrediction) throws SQLException {
        matchPredictionRepository.updateMatchPrediction(matchPrediction);
        return Response.status(201).build();
    }
}

