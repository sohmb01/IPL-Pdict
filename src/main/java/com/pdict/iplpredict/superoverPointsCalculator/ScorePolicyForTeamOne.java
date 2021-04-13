package com.pdict.iplpredict.superoverPointsCalculator;

import com.pdict.iplpredict.entities.Match;
import com.pdict.iplpredict.entities.SuperOver;
import com.pdict.iplpredict.entities.UserMatchPrediction;

public class ScorePolicyForTeamOne implements SuperoverPointsPolicy {
    @Override
    public Boolean isApplicable(UserMatchPrediction userMatchPrediction, SuperOver superOver) {
        return superOver.teamScore1 >= userMatchPrediction.teamLow1 && superOver.teamScore1 <= userMatchPrediction.teamHigh1;
    }

    @Override
    public Integer getPoints() {
        return 5;
    }
}
