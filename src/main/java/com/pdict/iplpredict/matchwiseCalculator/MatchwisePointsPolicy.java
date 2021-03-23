package com.pdict.iplpredict.matchwiseCalculator;

import com.pdict.iplpredict.entities.Match;
import com.pdict.iplpredict.entities.UserMatchwisePrediction;

public interface MatchwisePointsPolicy {
    public Boolean isApplicable(UserMatchwisePrediction userMatchwisePrediction, Match match);
    public Integer getPoints(UserMatchwisePrediction userMatchwisePrediction, Match match);
}
