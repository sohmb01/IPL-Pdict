package com.pdict.iplpredict.matchPointsCalculator;

import com.pdict.iplpredict.entities.Match;
import com.pdict.iplpredict.entities.UserMatchPrediction;

public class TeamOnePointsPolicy implements MatchPointsPolicy {
    @Override
    public Boolean isApplicable(UserMatchPrediction userMatchPrediction, Match match) {
        return match.teamScore1 >= userMatchPrediction.teamLow1 && match.teamScore1 <= userMatchPrediction.teamHigh1;
    }

    @Override
    public Integer getPoints(UserMatchPrediction userMatchPrediction, Match match) {
        return 5;
    }
}
