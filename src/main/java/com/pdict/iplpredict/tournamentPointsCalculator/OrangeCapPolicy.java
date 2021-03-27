package com.pdict.iplpredict.tournamentPointsCalculator;

import com.pdict.iplpredict.entities.Tournament;
import com.pdict.iplpredict.entities.UserTournamentPrediction;

public class OrangeCapPolicy implements TournamentPointsPolicy {
    @Override
    public Boolean isApplicable(UserTournamentPrediction userTournamentPrediction, Tournament tournament) {
        return userTournamentPrediction.orangeCaps.contains(tournament.orangeCap);
    }

    @Override
    public Integer getPoints(UserTournamentPrediction userTournamentPrediction, Tournament tournament) {
        return 50;
    }
}
