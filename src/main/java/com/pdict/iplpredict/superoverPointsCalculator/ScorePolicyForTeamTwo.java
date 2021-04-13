package com.pdict.iplpredict.superoverPointsCalculator;

import com.pdict.iplpredict.entities.Match;
import com.pdict.iplpredict.entities.SuperOver;
import com.pdict.iplpredict.entities.UserMatchPrediction;

public class ScorePolicyForTeamTwo implements SuperoverPointsPolicy{
    @Override
    public Boolean isApplicable(UserMatchPrediction userMatchPrediction, SuperOver superOver) {
        return superOver.teamScore2 >= userMatchPrediction.teamLow2 && superOver.teamScore2 <= userMatchPrediction.teamHigh2;
    }

    @Override
    public Integer getPoints() {
        return 5;
    }
}
