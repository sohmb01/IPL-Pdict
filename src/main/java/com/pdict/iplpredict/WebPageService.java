package com.pdict.iplpredict;

import com.sun.jersey.api.view.Viewable;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Path("/")
public class WebPageService {

//    @GET
//    @Produces(MediaType.TEXT_HTML)
//    public Viewable getUser(@PathParam("username") String userName) throws SQLException {
//        Map<String, String> model = new HashMap<>();
//        model.put("hello", "Hello");
//        model.put("world", "World");
//        return new Viewable("/", model);
//    }
@GET
@Produces(MediaType.TEXT_PLAIN)
public String getUser() throws SQLException {
    return "HELLO WORLD";
}
}