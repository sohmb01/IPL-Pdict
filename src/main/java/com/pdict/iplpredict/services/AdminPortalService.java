package com.pdict.iplpredict.services;

import com.pdict.iplpredict.database.MatchPointsRepository;
import com.pdict.iplpredict.database.MatchRepository;
import com.pdict.iplpredict.database.UserMatchPredictionRepository;
import com.pdict.iplpredict.entities.Match;
import com.pdict.iplpredict.entities.UserMatchPrediction;
import com.pdict.iplpredict.matchPointsCalculator.MatchPointsCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.time.Instant;
import java.util.List;

@Path("/adminPortal")
public class AdminPortalService {
    Logger logger = LoggerFactory.getLogger(AdminPortalService.class);
    UserMatchPredictionRepository userMatchPredictionRepository = new UserMatchPredictionRepository();
    MatchRepository matchRepository = new MatchRepository();
    MatchPointsRepository matchPointsRepository = new MatchPointsRepository();
    MatchPointsCalculator matchPointsCalculator = new MatchPointsCalculator();

    @POST
    @Path("/matchEnded")
    public Response matchEnded(Match match) throws SQLException {
        logger.info(Instant.now()+" RECEIVED POST: /matchEnded "+match);

        try {
            matchRepository.updateMatch(match);

            List<UserMatchPrediction> userMatchPredictions = userMatchPredictionRepository.getUserMatchPredictionsByMatchId(match.matchId);

            for (UserMatchPrediction userMatchPrediction : userMatchPredictions) {
                List<UserMatchPrediction> superOverPredictions = userMatchPredictionRepository.getUserSuperOverPredictionsByMatchIdAndUsername(match.matchId, userMatchPrediction.username);
                List<Match> superOvers = matchRepository.getSuperOversOfMatch(match.matchId);

                Integer points = matchPointsCalculator.getMatchPoints(userMatchPrediction, match, superOverPredictions, superOvers);

                matchPointsRepository.insertPoints(userMatchPrediction.username, match.matchId, points);
            }

            logger.info(Instant.now() + " POINTS UPDATED: /matchEnded " + match);
        }
        catch (SQLException sqlException) {
            logger.error(Instant.now()+" DBOPFAILURE POST: /matchEnded "+match);
            return Response.status(500).build();
        }
        return Response.status(200).build();
    }
}
