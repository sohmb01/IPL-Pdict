package com.pdict.iplpredict.matchPointsCalculator;

import com.pdict.iplpredict.entities.Match;
import com.pdict.iplpredict.entities.UserMatchPrediction;

import java.util.ArrayList;
import java.util.List;

public class MatchPointsCalculator {
    List<MatchPointsPolicy> matchPointsPolicies = new ArrayList<>();

    public MatchPointsCalculator() {
        matchPointsPolicies.add(new CorrectMatchResultPolicy());
        matchPointsPolicies.add(new TeamOnePointsPolicy());
        matchPointsPolicies.add(new TeamTwoPointsPolicy());
        matchPointsPolicies.add(new WicketsPolicy());
    }

    public Integer getMatchPoints(UserMatchPrediction userMatchPrediction, Match match) {
        Integer points = 0;

        for(MatchPointsPolicy matchPointsPolicy : matchPointsPolicies) {
            if(matchPointsPolicy.isApplicable(userMatchPrediction, match)) {
                points += matchPointsPolicy.getPoints(userMatchPrediction, match);
            }
        }

        return points;
    }
}
