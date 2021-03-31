package com.pdict.iplpredict.superoverPointsCalculator;

import com.pdict.iplpredict.entities.Match;
import com.pdict.iplpredict.entities.UserMatchPrediction;

public class ScorePolicyForTeamOne implements SuperoverPointsPolicy {
    @Override
    public Boolean isApplicable(UserMatchPrediction userMatchPrediction, Match match) {
        return match.teamScore1 >= userMatchPrediction.teamLow1 && match.teamScore1 <= userMatchPrediction.teamHigh1;
    }

    @Override
    public Integer getPoints() {
        return 5;
    }
}
