package com.pdict.iplpredict.matchwiseCalculator;

import com.pdict.iplpredict.entities.Match;
import com.pdict.iplpredict.entities.UserMatchwisePrediction;

public class TeamOnePointsPolicy implements MatchwisePointsPolicy {
    @Override
    public Boolean isApplicable(UserMatchwisePrediction userMatchwisePrediction, Match match) {
        return match.teamScore1 >= userMatchwisePrediction.teamLow1 && match.teamScore1 <= userMatchwisePrediction.teamHigh1;
    }

    @Override
    public Integer getPoints(UserMatchwisePrediction userMatchwisePrediction, Match match) {
        return 5;
    }
}
