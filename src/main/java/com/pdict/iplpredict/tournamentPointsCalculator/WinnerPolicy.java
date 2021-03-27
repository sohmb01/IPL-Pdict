package com.pdict.iplpredict.tournamentPointsCalculator;

import com.pdict.iplpredict.entities.Tournament;
import com.pdict.iplpredict.entities.UserTournamentPrediction;

public class WinnerPolicy implements TournamentPointsPolicy {
    @Override
    public Boolean isApplicable(UserTournamentPrediction userTournamentPrediction, Tournament tournament) {
        return userTournamentPrediction.winningTeam.equals(tournament.winningTeam);
    }

    @Override
    public Integer getPoints(UserTournamentPrediction userTournamentPrediction, Tournament tournament) {
        return 80 ;
    }
}
