package com.pdict.iplpredict.matchwiseCalculator;

import com.pdict.iplpredict.entities.Match;
import com.pdict.iplpredict.entities.User;
import com.pdict.iplpredict.entities.UserMatchwisePrediction;

import java.util.ArrayList;
import java.util.List;

public class MatchwisePointsCalculator {
    List<MatchwisePointsPolicy> matchwisePointsPolicies = new ArrayList<>();

    public MatchwisePointsCalculator() {
        matchwisePointsPolicies.add(new CorrectMatchResultPolicy());
        matchwisePointsPolicies.add(new TeamOnePointsPolicy());
        matchwisePointsPolicies.add(new TeamTwoPointsPolicy());
        matchwisePointsPolicies.add(new WicketsPolicy());
    }

    public Integer getMatchwisePoints(UserMatchwisePrediction userMatchwisePrediction, Match match) {
        Integer points = 0;

        for(MatchwisePointsPolicy matchwisePointsPolicy : matchwisePointsPolicies) {
            if(matchwisePointsPolicy.isApplicable(userMatchwisePrediction, match)) {
                points += matchwisePointsPolicy.getPoints(userMatchwisePrediction, match);
            }
        }

        return points;
    }
}
