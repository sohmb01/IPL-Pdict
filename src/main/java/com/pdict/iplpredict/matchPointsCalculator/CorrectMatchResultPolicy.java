package com.pdict.iplpredict.matchPointsCalculator;

import com.pdict.iplpredict.entities.Match;
import com.pdict.iplpredict.entities.UserMatchPrediction;

public class CorrectMatchResultPolicy implements MatchPointsPolicy {
    @Override
    public Boolean isApplicable(UserMatchPrediction userMatchPrediction, Match match) {
        return userMatchPrediction.teamWin == match.teamWin;
    }

    @Override
    public Integer getPoints(UserMatchPrediction userMatchPrediction, Match match) {
        return userMatchPrediction.favTeam.equals(match.teamWin) ? 12 : 10;
    }
}
