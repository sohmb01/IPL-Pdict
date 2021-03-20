package com.pdict.iplpredict;

import com.pdict.iplpredict.database.MatchwisePredictionRepository;
import com.pdict.iplpredict.entities.MatchwisePrediction;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("/matchwisePrediction")
public class MatchwisePredictionService {
    private MatchwisePredictionRepository matchwisePredictionRepository = new MatchwisePredictionRepository();

    @GET
    @Path("/getMatchwisePrediction/{matchId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMatchwisePrediction(@PathParam("username") String username, @PathParam("matchId") Integer matchId) throws SQLException {
        MatchwisePrediction matchwisePrediction = matchwisePredictionRepository.getMatchwisePredictionByMatchIdAndUsername(username, matchId);

        return Response.ok()
                .entity(matchwisePrediction)
                .build();
    }

    @POST
    @Path("/createMatchwisePrediction/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createMatchwisePrediction(MatchwisePrediction matchwisePrediction) throws SQLException {
        matchwisePredictionRepository.insertMatchwisePrediction(matchwisePrediction);
        return Response.status(201).build();
    }

    @PUT
    @Path("/updateMatchwisePrediction")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateMatchwisePrediction(MatchwisePrediction matchwisePrediction) throws SQLException{
        matchwisePredictionRepository.updateMatchwisePrediction(matchwisePrediction);
        return Response.status(201).build();
    }


}

