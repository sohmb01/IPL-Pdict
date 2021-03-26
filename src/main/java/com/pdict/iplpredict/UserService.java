package com.pdict.iplpredict;

import com.pdict.iplpredict.database.UserRepository;
import com.pdict.iplpredict.entities.User;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.sql.SQLException;

@Path("/user")
public class UserService {
    private UserRepository userRepository = new UserRepository();

    @GET
    @Path("/getUser/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("username") String userName) {
        User user = null;
        try {
            user = userRepository.getUserByUserName(userName);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return Response.ok()
                .entity(user)
                .build();
    }

    @POST
    @Path("/createUser/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(User user) throws SQLException {
        userRepository.insertUser(user);
        return Response.status(201).build();
    }

    @PUT
    @Path("/updateUser")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(User user) throws SQLException{
        userRepository.updateUser(user);
        return Response.status(201).build();
    }

    @DELETE
    @Path("/deleteUser/{username}")
    public Response deleteUser(@PathParam("username") String userName) throws SQLException {
        userRepository.deleteUserByUserName(userName);
        return Response.status(200).build();
    }
}