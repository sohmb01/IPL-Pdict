package com.pdict.iplpredict.matchwiseCalculator;

import com.pdict.iplpredict.entities.Match;
import com.pdict.iplpredict.entities.UserMatchwisePrediction;

public class WicketsPolicy implements MatchwisePointsPolicy {
    @Override
    public Boolean isApplicable(UserMatchwisePrediction userMatchwisePrediction, Match match) {
        return Math.abs(match.wickets - userMatchwisePrediction.wickets) <= 1;
    }

    @Override
    public Integer getPoints(UserMatchwisePrediction userMatchwisePrediction, Match match) {
        return 10;
    }
}
