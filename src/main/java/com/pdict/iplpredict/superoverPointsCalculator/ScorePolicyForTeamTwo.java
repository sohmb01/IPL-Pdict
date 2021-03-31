package com.pdict.iplpredict.superoverPointsCalculator;

import com.pdict.iplpredict.entities.Match;
import com.pdict.iplpredict.entities.UserMatchPrediction;

public class ScorePolicyForTeamTwo implements SuperoverPointsPolicy{
    @Override
    public Boolean isApplicable(UserMatchPrediction userMatchPrediction, Match match) {
        return match.teamScore2 >= userMatchPrediction.teamLow2 && match.teamScore2 <= userMatchPrediction.teamHigh2;
    }

    @Override
    public Integer getPoints() {
        return 5;
    }
}
