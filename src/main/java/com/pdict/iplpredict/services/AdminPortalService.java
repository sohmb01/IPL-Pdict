package com.pdict.iplpredict.services;

import com.pdict.iplpredict.database.*;
import com.pdict.iplpredict.entities.*;
import com.pdict.iplpredict.matchPointsCalculator.MatchPointsCalculator;
import com.pdict.iplpredict.tournamentPointsCalculator.TournamentPointsCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.time.Instant;
import java.util.List;

@Path("/adminPortal")
public class AdminPortalService {
    Logger logger = LoggerFactory.getLogger(AdminPortalService.class);
    UserMatchPredictionRepository userMatchPredictionRepository = new UserMatchPredictionRepository();
    MatchRepository matchRepository = new MatchRepository();
    SuperOverRepository superOverRepository = new SuperOverRepository();
    MatchPointsCalculator matchPointsCalculator = new MatchPointsCalculator();
    UserTournamentPredictionRepository userTournamentPredictionRepository = new UserTournamentPredictionRepository();
    TournamentRepository tournamentRepository = new TournamentRepository();
    TournamentPointsCalculator tournamentPointsCalculator = new TournamentPointsCalculator();
    PointsRepository pointsRepository = new PointsRepository();
    LoginSessionRepository loginSessionRepository = new LoginSessionRepository();

    @POST
    @Path("/matchEnded")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response matchEnded(@HeaderParam("HeaderUsername") String headerUsername, @HeaderParam("AccessToken") String accessToken, Match match) {
        logger.info(Instant.now()+" RECEIVED POST: /matchEnded "+match);

        try {
            String activeToken = loginSessionRepository.getActiveToken("admin");
            if(!headerUsername.contentEquals("admin") || activeToken==null || !activeToken.contentEquals(accessToken)) {
                logger.info(Instant.now()+" AUTHORIZATION FAILURE POST: /matchEnded "+match);
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }

            matchRepository.upsertMatch(match);

            List<UserMatchPrediction> userMatchPredictions = userMatchPredictionRepository.getUserMatchPredictionsByMatchId(match.matchId);

            for (UserMatchPrediction userMatchPrediction : userMatchPredictions) {
                List<UserMatchPrediction> superOverPredictions = userMatchPredictionRepository.getUserSuperOverPredictionsByMatchIdAndUsername(match.matchId, userMatchPrediction.userName);
                List<SuperOver> superOvers = superOverRepository.getSuperOversOfMatch(match.matchId);
                Integer points = matchPointsCalculator.getMatchPoints(userMatchPrediction, match, superOverPredictions, superOvers);

                pointsRepository.insertMatchPoints(userMatchPrediction.userName, match.matchId, points);
            }

            logger.info(Instant.now() + " POINTS UPDATED: /matchEnded " + match);
        }
        catch (SQLException sqlException) {
            logger.error(Instant.now()+" DBOPFAILURE POST: /matchEnded "+match);
            return Response.status(500).build();
        }
        return Response.status(200).build();
    }

    @POST
    @Path("/tournamentEnded")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response tournamentEnded(@HeaderParam("HeaderUsername") String headerUsername, @HeaderParam("AccessToken") String accessToken, Tournament tournament) {
        logger.info(Instant.now()+" RECEIVED POST: /tournamentEnded "+tournament);

        try {
            String activeToken = loginSessionRepository.getActiveToken("admin");
            if(!headerUsername.contentEquals("admin") || activeToken==null || !activeToken.contentEquals(accessToken)) {
                logger.info(Instant.now()+" AUTHORIZATION FAILURE POST: /tournamentEnded "+tournament);
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }

            tournamentRepository.updateTournament(tournament);

            List<UserTournamentPrediction> userTournamentPredictions = userTournamentPredictionRepository.getUserTournamentPredictionsByTournamentYear(tournament.tournamentYear);

            for (UserTournamentPrediction userTournamentPrediction : userTournamentPredictions) {
                Integer points = tournamentPointsCalculator.getTournamentPoints(userTournamentPrediction, tournament);

                pointsRepository.insertTournamentPoints(userTournamentPrediction.userName, tournament.tournamentYear, points);
            }

            logger.info(Instant.now() + " POINTS UPDATED: /tournamentEnded " + tournament);
        }
        catch (SQLException sqlException) {
            logger.error(Instant.now()+" DBOPFAILURE POST: /tournamentEnded "+tournament);
            return Response.status(500).build();
        }
        return Response.status(200).build();
    }
}
