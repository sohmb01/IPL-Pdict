package com.pdict.iplpredict.superoverPointsCalculator;

import com.pdict.iplpredict.entities.Match;
import com.pdict.iplpredict.entities.SuperOver;
import com.pdict.iplpredict.entities.UserMatchPrediction;

public class WicketsPolicy implements SuperoverPointsPolicy{
    @Override
    public Boolean isApplicable(UserMatchPrediction userMatchPrediction, SuperOver superOver) {
        return superOver.wickets == userMatchPrediction.wickets;
    }

    @Override
    public Integer getPoints() {
        return 10;
    }
}
