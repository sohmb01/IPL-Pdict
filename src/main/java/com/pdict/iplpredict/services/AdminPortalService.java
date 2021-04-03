package com.pdict.iplpredict.services;

import com.pdict.iplpredict.database.*;
import com.pdict.iplpredict.entities.Match;
import com.pdict.iplpredict.entities.Tournament;
import com.pdict.iplpredict.entities.UserMatchPrediction;
import com.pdict.iplpredict.entities.UserTournamentPrediction;
import com.pdict.iplpredict.matchPointsCalculator.MatchPointsCalculator;
import com.pdict.iplpredict.tournamentPointsCalculator.TournamentPointsCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
    MatchPointsCalculator matchPointsCalculator = new MatchPointsCalculator();
    UserTournamentPredictionRepository userTournamentPredictionRepository = new UserTournamentPredictionRepository();
    TournamentRepository tournamentRepository = new TournamentRepository();
    TournamentPointsCalculator tournamentPointsCalculator = new TournamentPointsCalculator();
    PointsRepository pointsRepository = new PointsRepository();

    @POST
    @Path("/matchEnded")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response matchEnded(Match match) {
        logger.info(Instant.now()+" RECEIVED POST: /matchEnded "+match);

        try {
            matchRepository.updateMatch(match);

            List<UserMatchPrediction> userMatchPredictions = userMatchPredictionRepository.getUserMatchPredictionsByMatchId(match.matchId);

            for (UserMatchPrediction userMatchPrediction : userMatchPredictions) {
                List<UserMatchPrediction> superOverPredictions = userMatchPredictionRepository.getUserSuperOverPredictionsByMatchIdAndUsername(match.matchId, userMatchPrediction.userName);
                List<Match> superOvers = matchRepository.getSuperOversOfMatch(match.matchId);

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
    public Response tournamentEnded(Tournament tournament) {
        logger.info(Instant.now()+" RECEIVED POST: /tournamentEnded "+tournament);

        try {
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
