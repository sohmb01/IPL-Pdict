package com.pdict.iplpredict.webPageServices;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/matchPredictionPage")
public class MatchPredictionPage {

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response getMainPage() {
        String matchPredictionHtml = Utils.loadFile("webPageResources/matchPrediction/match_prediction.html");

        return Response.ok()
                .entity(matchPredictionHtml)
                .build();
    }

    @GET
    @Path("/match_prediction.js")
    @Produces(MediaType.TEXT_PLAIN)
    public String getMatchPredictionJs() {
        String matchPredictionJs = Utils.loadFile("webPageResources/matchPrediction/match_prediction.js");

        return matchPredictionJs;
    }

    @GET
    @Path("/match_prediction.css")
    @Produces(MediaType.TEXT_PLAIN)
    public String getMatchPredictionCss() {
        String matchPredictionCss = Utils.loadFile("webPageResources/matchPrediction/match_prediction.css");

        return matchPredictionCss;
    }
}