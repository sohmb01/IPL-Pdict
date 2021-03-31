package com.pdict.iplpredict.superoverPointsCalculator;

import com.pdict.iplpredict.entities.UserMatchPrediction;
import com.pdict.iplpredict.entities.Match;

public interface SuperoverPointsPolicy {
    public Boolean isApplicable(UserMatchPrediction userMatchPrediction, Match match);
    public Integer getPoints();
}
