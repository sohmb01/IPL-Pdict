package com.pdict.iplpredict.services;

import com.pdict.iplpredict.database.LoginSessionRepository;
import com.pdict.iplpredict.database.SuperOverRepository;
import com.pdict.iplpredict.entities.SuperOver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.time.Instant;
import java.util.List;

@Path("/superOver")
public class SuperOverService {
    LoginSessionRepository loginSessionRepository = new LoginSessionRepository();
    Logger logger = LoggerFactory.getLogger(SuperOverService.class);
    SuperOverRepository superOverRepository = new SuperOverRepository();

    @GET
    @Path("/getAllSuperOversOfMatch/{matchId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllMatches(@HeaderParam("HeaderUsername") String headerUsername, @HeaderParam("AccessToken") String accessToken, @PathParam("matchId") String matchId)  {
        logger.info(Instant.now()+" RECEIVED GET: /getAllSuperOversOfMatch/"+matchId);

        List<SuperOver> superOvers = null;
        try {
            String activeToken = loginSessionRepository.getActiveToken(headerUsername);
            if(activeToken==null || !activeToken.contentEquals(accessToken)) {
                logger.info(Instant.now()+" AUTHORIZATION FAILURE GET: /getAllSuperOversOfMatch/"+matchId);
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }

            superOvers = superOverRepository.getSuperOversOfMatch(matchId);
        } catch (SQLException sqlException) {
            logger.error(Instant.now()+" DBOPFAILURE GET: /getAllSuperOversOfMatch/"+matchId, sqlException);
            return Response.status(500).build();
        }

        logger.info(Instant.now()+" DBOPSUCCESS GET: /getAllSuperOversOfMatch/"+matchId);

        return Response.ok()
                .entity(superOvers)
                .build();
    }

    @POST
    @Path("/upsertSuperOver")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createMatch(@HeaderParam("HeaderUsername") String headerUsername, @HeaderParam("AccessToken") String accessToken, SuperOver superOver)  {
        logger.info(Instant.now()+" RECEIVED POST: /upsertSuperOver "+superOver);

        try {
            String activeToken = loginSessionRepository.getActiveToken("admin");
            if(!headerUsername.contentEquals("admin") || activeToken==null || !activeToken.contentEquals(accessToken)) {
                logger.info(Instant.now()+" AUTHORIZATION FAILURE POST: /upsertSuperOver "+superOver);
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }

            superOverRepository.upsertSuperOver(superOver);
        } catch (SQLException sqlException) {
            logger.error(Instant.now()+" DBOPFAILURE POST: /upsertSuperOver "+superOver, sqlException);
            return Response.status(500).build();
        }

        logger.info(Instant.now()+" DBOPSUCCESS POST: /upsertSuperOver "+superOver);

        return Response.status(201).build();
    }
}
