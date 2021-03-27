package com.pdict.iplpredict.tournamentPointsCalculator;

import com.pdict.iplpredict.entities.Tournament;
import com.pdict.iplpredict.entities.UserTournamentPrediction;

public class BonusPolicy implements TournamentPointsPolicy {
    @Override
    public Boolean isApplicable(UserTournamentPrediction userTournamentPrediction, Tournament tournament) {
        return tournament.winningTeam.equals(userTournamentPrediction.favTeam) ||
               tournament.runnerUpTeam.equals(userTournamentPrediction.favTeam);
    }

    @Override
    public Integer getPoints(UserTournamentPrediction userTournamentPrediction, Tournament tournament) {
        return 20 ;
    }
}
