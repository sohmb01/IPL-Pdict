package com.pdict.iplpredict.matchwiseCalculator;

import com.pdict.iplpredict.entities.Match;
import com.pdict.iplpredict.entities.UserMatchwisePrediction;

public class CorrectMatchResultPolicy implements MatchwisePointsPolicy {
    @Override
    public Boolean isApplicable(UserMatchwisePrediction userMatchwisePrediction, Match match) {
        return userMatchwisePrediction.teamWin == match.teamWin;
    }

    @Override
    public Integer getPoints(UserMatchwisePrediction userMatchwisePrediction, Match match) {
        return userMatchwisePrediction.favTeam.equals(match.teamWin) ? 12 : 10;
    }
}
