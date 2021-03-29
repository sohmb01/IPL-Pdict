package com.pdict.iplpredict;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.io.IOException;
import java.io.InputStream;

//TODO: Implement these paths using regex
@Path("/")
public class WebPageService {

    @GET
    @Path("/adminPage/{file}")
    public InputStream getAdminPageResource(@PathParam("file") String file) throws IOException {
        return WebPageServiceUtils.loadFile("webPageResources/admin/"+file);
    }

    @GET
    @Path("/assetsResource/brand/{file}")
    public InputStream getAssetsResource(@PathParam("file") String file) throws IOException {
        return WebPageServiceUtils.loadFile("webPageResources/assets/brand/"+file);
    }

    @GET
    @Path("/assetsResource/dist/{dir}/{file}")
    public InputStream getAssetsResource(@PathParam("dir") String dir, @PathParam("file") String file) throws IOException {
        return WebPageServiceUtils.loadFile("webPageResources/assets/dist/"+dir+"/"+file);
    }

    @GET
    @Path("/coverPage/{file}")
    public InputStream getCoverPageResource(@PathParam("file") String file) throws IOException {
        return WebPageServiceUtils.loadFile("webPageResources/cover/"+file);
    }

    @GET
    @Path("/landingPage/{file}")
    public InputStream getLandingPageResource(@PathParam("file") String file) throws IOException {
        return WebPageServiceUtils.loadFile("webPageResources/landing/"+file);
    }

    @GET
    @Path("/leaderboardPage/{leaderboardType}/{file}")
    public InputStream getLandingPageResource(@PathParam("leaderboardType") String leaderboardType, @PathParam("file") String file) throws IOException {
        return WebPageServiceUtils.loadFile("webPageResources/leaderboard/"+leaderboardType+"/"+file);
    }

    @GET
    @Path("/matchPredictionPage/{file}")
    public InputStream getMatchPredictionPageResource(@PathParam("file") String file) throws IOException {
        return WebPageServiceUtils.loadFile("webPageResources/matchPrediction/"+file);
    }

    @GET
    @Path("/signInPage/{file}")
    public InputStream getSignInPageResource(@PathParam("file") String file) throws IOException {
        return WebPageServiceUtils.loadFile("webPageResources/signIn/"+file);
    }

    @GET
    @Path("/tournamentPredictionPage/{file}")
    public InputStream getTournamentPredictionPageResource(@PathParam("file") String file) throws IOException {
        return WebPageServiceUtils.loadFile("webPageResources/tournamentPrediction/"+file);
    }

    @GET
    @Path("/userPage/{file}")
    public InputStream getUserPageResource(@PathParam("file") String file) throws IOException {
        return WebPageServiceUtils.loadFile("webPageResources/user/"+file);
    }
}
