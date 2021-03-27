package com.pdict.iplpredict.matchPointsCalculator;

import com.pdict.iplpredict.entities.Match;
import com.pdict.iplpredict.entities.UserMatchPrediction;

public interface MatchPointsPolicy {
    public Boolean isApplicable(UserMatchPrediction userMatchPrediction, Match match);
    public Integer getPoints(UserMatchPrediction userMatchPrediction, Match match);
}
