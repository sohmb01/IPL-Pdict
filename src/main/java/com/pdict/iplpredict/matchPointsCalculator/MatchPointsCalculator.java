package com.pdict.iplpredict.matchPointsCalculator;

import com.pdict.iplpredict.database.SuperOverRepository;
import com.pdict.iplpredict.entities.Match;
import com.pdict.iplpredict.entities.SuperOver;
import com.pdict.iplpredict.entities.UserMatchPrediction;
import com.pdict.iplpredict.superoverPointsCalculator.SuperoverPointsCalculator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MatchPointsCalculator {
    List<MatchPointsPolicy> matchPointsPolicies = new ArrayList<>();

    public MatchPointsCalculator() {
        matchPointsPolicies.add(new CorrectMatchResultPolicy());
        matchPointsPolicies.add(new TeamOnePointsPolicy());
        matchPointsPolicies.add(new TeamTwoPointsPolicy());
        matchPointsPolicies.add(new WicketsPolicy());
    }

    public Integer getMatchPoints(UserMatchPrediction userMatchPrediction, Match match, List<UserMatchPrediction> superOverPredictions, List<SuperOver> superOvers) {
        Integer points = 0;

        for(MatchPointsPolicy matchPointsPolicy : matchPointsPolicies) {
            if(matchPointsPolicy.isApplicable(userMatchPrediction, match)) {
                points += matchPointsPolicy.getPoints(userMatchPrediction, match);
            }
        }

        SuperoverPointsCalculator superoverPointsCalculator = new SuperoverPointsCalculator();

        Iterator<UserMatchPrediction> superOverPredictionsIterator = superOverPredictions.iterator();
        Iterator<SuperOver> superOversIterator = superOvers.iterator();

        while(superOverPredictionsIterator.hasNext() && superOversIterator.hasNext()) {
            UserMatchPrediction superOverPrediction = superOverPredictionsIterator.next();
            SuperOver superOver = superOversIterator.next();

            points += superoverPointsCalculator.getSuperoverPoints(superOverPrediction, superOver);
        }

        return points;
    }
}
