package com.pdict.iplpredict.services;

import com.pdict.iplpredict.database.UserRepository;
import com.pdict.iplpredict.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.sql.SQLException;
import java.time.Instant;

@Path("/user")
public class UserService {
    private UserRepository userRepository = new UserRepository();
    Logger logger = LoggerFactory.getLogger(UserService.class);

    @GET
    @Path("/getUser/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("username") String userName) {
        logger.info(Instant.now()+" RECEIVED GET: /getUser/"+userName);

        User user = null;
        try {
            user = userRepository.getUserByUserName(userName);
        } catch (SQLException sqlException) {
            logger.error(Instant.now()+" DBOPFAILURE GET: /getUser/"+userName, sqlException);
            return Response.status(500).build();
        }

        logger.info(Instant.now()+" DBOPSUCCESS GET: /getUser/"+userName);

        return Response.ok()
                .entity(user)
                .build();
    }

    @POST
    @Path("/createUser/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(User user) {
        logger.info(Instant.now()+" RECEIVED POST: /createUser "+user);

        try {
            userRepository.insertUser(user);
        } catch (SQLException sqlException) {
            logger.error(Instant.now()+" DBOPFAILURE POST: /createUser "+user, sqlException);
            return Response.status(500).build();
        }

        logger.info(Instant.now()+" DBOPSUCCESS POST: /createUser "+user);

        return Response.status(201).build();
    }

    @PUT
    @Path("/updateUser")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(User user) {
        logger.info(Instant.now()+" RECEIVED PUT: /updateUser "+user);

        try {
            userRepository.updateUser(user);
        } catch (SQLException sqlException) {
            logger.error(Instant.now()+" DBOPFAILURE PUT: /updateUser "+user, sqlException);
            return Response.status(500).build();
        }

        logger.info(Instant.now()+" DBOPSUCCESS PUT: /updateUser "+user);

        return Response.status(201).build();
    }

    @DELETE
    @Path("/deleteUser/{username}")
    public Response deleteUser(@PathParam("username") String userName) {
        logger.info("RECEIVED DELETE: /deleteUser/"+userName);

        try {
            userRepository.deleteUserByUserName(userName);
        } catch (SQLException sqlException) {
            logger.error(Instant.now()+" DBOPFAILURE DELETE: /deleteUser/"+userName, sqlException);
            return Response.status(500).build();
        }

        logger.info(Instant.now()+" DBOPSUCCESS DELETE: /deleteUser/"+userName);

        return Response.status(200).build();
    }
}