package com.pdict.iplpredict.matchwiseCalculator;

import com.pdict.iplpredict.entities.Match;
import com.pdict.iplpredict.entities.UserMatchwisePrediction;

public class TeamTwoPointsPolicy implements MatchwisePointsPolicy {
    @Override
    public Boolean isApplicable(UserMatchwisePrediction userMatchwisePrediction, Match match) {
        return match.teamScore2 >= userMatchwisePrediction.teamLow2 && match.teamScore2 <= userMatchwisePrediction.teamHigh2;
    }

    @Override
    public Integer getPoints(UserMatchwisePrediction userMatchwisePrediction, Match match) {
        return 5;
    }
}
