package com.pdict.iplpredict.tournamentPointsCalculator;

import com.pdict.iplpredict.entities.Tournament;
import com.pdict.iplpredict.entities.UserTournamentPrediction;

public interface TournamentPointsPolicy {
    public Boolean isApplicable(UserTournamentPrediction prediction , Tournament tournament);
    public Integer getPoints(UserTournamentPrediction prediction , Tournament tournament);
}
