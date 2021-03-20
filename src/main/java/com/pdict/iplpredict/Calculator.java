package com.pdict.iplpredict;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;

@Path("calc")
public class Calculator {
    @GET
    @Consumes("text/plain")
    @Produces("text/plain")
    @Path("addTwoNumbers")
    public String add(@MatrixParam("firstNumber") int n1, @MatrixParam("secondNumber") int n2) {
        return String.valueOf(n1 + n2);
    }
}