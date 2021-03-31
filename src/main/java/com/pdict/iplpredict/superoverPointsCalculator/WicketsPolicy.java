package com.pdict.iplpredict.superoverPointsCalculator;

import com.pdict.iplpredict.entities.Match;
import com.pdict.iplpredict.entities.UserMatchPrediction;

public class WicketsPolicy implements SuperoverPointsPolicy{
    @Override
    public Boolean isApplicable(UserMatchPrediction userMatchPrediction, Match match) {
        return match.wickets == userMatchPrediction.wickets;
    }

    @Override
    public Integer getPoints() {
        return 10;
    }
}
