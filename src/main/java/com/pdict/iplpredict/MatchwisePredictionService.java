
package com.pdict.iplpredict;

        import com.pdict.iplpredict.database.*;
        import com.pdict.iplpredict.entities.*;

        import javax.ws.rs.*;
        import javax.ws.rs.core.*;
        import java.sql.SQLException;
        import java.util.List;

@Path("/match")
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

