package com.pdict.iplpredict.tournamentPointsCalculator;

import com.pdict.iplpredict.entities.Tournament;
import com.pdict.iplpredict.entities.UserTournamentPrediction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TopFourPolicy implements TournamentPointsPolicy {
    Map<Integer, Integer> correctGuessesToPoints = new HashMap<>();

    public TopFourPolicy() {
        correctGuessesToPoints.put(0,0);
        correctGuessesToPoints.put(1,10);
        correctGuessesToPoints.put(2,20);
        correctGuessesToPoints.put(3,30);
        correctGuessesToPoints.put(4,50);
    }

    @Override
    public Boolean isApplicable(UserTournamentPrediction userTournamentPrediction, Tournament tournament) {
        return  Boolean.TRUE;
    }

    @Override
    public Integer getPoints(UserTournamentPrediction userTournamentPrediction, Tournament tournament) {
        Integer numberOfCorrectSemifinalists = getNumberOfCorrectSemifinalists(userTournamentPrediction.semiFinalists, tournament.semiFinalists);
        return correctGuessesToPoints.get(numberOfCorrectSemifinalists);
    }

    private Integer getNumberOfCorrectSemifinalists(List<String> predictedSemifinalists, List<String> actualSemifinalists) {
        return actualSemifinalists.stream()
                .filter(predictedSemifinalists::contains)
                .collect(Collectors.toSet())
                .size();
    }
}
