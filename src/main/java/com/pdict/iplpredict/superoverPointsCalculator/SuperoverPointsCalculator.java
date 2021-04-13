package com.pdict.iplpredict.superoverPointsCalculator;

import com.pdict.iplpredict.entities.SuperOver;
import com.pdict.iplpredict.entities.UserMatchPrediction;
import com.pdict.iplpredict.entities.Match;

import java.util.ArrayList;
import java.util.List;

public class SuperoverPointsCalculator {
    List<SuperoverPointsPolicy> superoverPointsPolicies = new ArrayList<>();

    public SuperoverPointsCalculator() {
        superoverPointsPolicies.add(new CorrectSuperoverResultPolicy());
        superoverPointsPolicies.add(new ScorePolicyForTeamOne());
        superoverPointsPolicies.add(new ScorePolicyForTeamTwo());
        superoverPointsPolicies.add(new WicketsPolicy());
    }

    public Integer getSuperoverPoints(UserMatchPrediction userMatchPrediction, SuperOver superOver){
        Integer points=0;
        for (SuperoverPointsPolicy superoverPointsPolicy : superoverPointsPolicies){
            if (superoverPointsPolicy.isApplicable(userMatchPrediction, superOver)){
                points+=superoverPointsPolicy.getPoints();
            }
        }
        return points;
    }
}
