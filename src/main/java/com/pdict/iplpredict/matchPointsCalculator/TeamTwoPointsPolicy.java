package com.pdict.iplpredict.matchPointsCalculator;

import com.pdict.iplpredict.entities.Match;
import com.pdict.iplpredict.entities.UserMatchPrediction;

public class TeamTwoPointsPolicy implements MatchPointsPolicy {
    @Override
    public Boolean isApplicable(UserMatchPrediction userMatchPrediction, Match match) {
        return match.teamScore2 >= userMatchPrediction.teamLow2 && match.teamScore2 <= userMatchPrediction.teamHigh2;
    }

    @Override
    public Integer getPoints(UserMatchPrediction userMatchPrediction, Match match) {
        return 5;
    }
}
