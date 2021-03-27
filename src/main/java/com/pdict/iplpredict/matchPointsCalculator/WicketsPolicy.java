package com.pdict.iplpredict.matchPointsCalculator;

import com.pdict.iplpredict.entities.Match;
import com.pdict.iplpredict.entities.UserMatchPrediction;

public class WicketsPolicy implements MatchPointsPolicy {
    @Override
    public Boolean isApplicable(UserMatchPrediction userMatchPrediction, Match match) {
        return Math.abs(match.wickets - userMatchPrediction.wickets) <= 1;
    }

    @Override
    public Integer getPoints(UserMatchPrediction userMatchPrediction, Match match) {
        return 10;
    }
}
