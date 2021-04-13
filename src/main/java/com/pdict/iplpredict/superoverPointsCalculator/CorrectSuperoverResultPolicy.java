package com.pdict.iplpredict.superoverPointsCalculator;

import com.pdict.iplpredict.entities.Match;
import com.pdict.iplpredict.entities.SuperOver;
import com.pdict.iplpredict.entities.UserMatchPrediction;

public class CorrectSuperoverResultPolicy implements SuperoverPointsPolicy{
    @Override
    public Boolean isApplicable(UserMatchPrediction userMatchPrediction, SuperOver superOver) {
        return userMatchPrediction.teamWin == superOver.teamWin;
    }

    @Override
    public Integer getPoints() {
        return 10;
    }
}
