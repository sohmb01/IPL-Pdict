package com.pdict.iplpredict.services;

import com.pdict.iplpredict.database.PredictionRepository;
import com.pdict.iplpredict.entities.Prediction;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.sql.SQLException;

@Path("/prediction")
public class PredictionService {
    private PredictionRepository predictionRepository = new PredictionRepository();

    @GET
    @Path("/getPrediction/{username}/{tournament_year}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPrediction(@PathParam("username") String userName ,@PathParam("tournament_year") Integer tournamentYear) throws SQLException {
        Prediction prediction = predictionRepository.getPrediction(userName , tournamentYear);

        return Response.ok()
                .entity(prediction)
                .build();
    }

    @POST
    @Path("/addPrediction/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPrediction(Prediction prediction) throws SQLException {
        predictionRepository.addPrediction( prediction);
        return Response.status(201).build();
    }

    @PUT
    @Path("/updatePrediction")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePrediction(Prediction prediction) throws SQLException{
        predictionRepository.updatePrediction(prediction);
        return Response.status(201).build();
    }

    @DELETE
    @Path("/deletePrediction/{username}/{tournament_year}")
    public Response deletePrediction(@PathParam("username") String userName ,@PathParam("tournament_year")  Integer tournamentYear) throws SQLException {
        predictionRepository.deletePrediction(userName , tournamentYear);
        return Response.status(200).build();
    }
}