package com.pdict.iplpredict.tournamentPointsCalculator;

import com.pdict.iplpredict.entities.Tournament;
import com.pdict.iplpredict.entities.UserTournamentPrediction;

public class RunnerupPolicy implements TournamentPointsPolicy {
    @Override
    public Boolean isApplicable(UserTournamentPrediction userTournamentPrediction, Tournament tournament) {
        return userTournamentPrediction.winningTeam.equals(tournament.runnerUpTeam);
    }

    @Override
    public Integer getPoints(UserTournamentPrediction userTournamentPrediction, Tournament tournament) {
        return 50;
    }
}
