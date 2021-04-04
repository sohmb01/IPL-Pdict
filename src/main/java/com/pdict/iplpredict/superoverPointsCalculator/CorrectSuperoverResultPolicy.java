package com.pdict.iplpredict.superoverPointsCalculator;

import com.pdict.iplpredict.entities.Match;
import com.pdict.iplpredict.entities.UserMatchPrediction;

public class CorrectSuperoverResultPolicy implements SuperoverPointsPolicy{
    @Override
    public Boolean isApplicable(UserMatchPrediction userMatchPrediction, Match match) {
        return userMatchPrediction.teamWin == match.teamWin;
    }

    @Override
    public Integer getPoints() {
        return 10;
    }
}