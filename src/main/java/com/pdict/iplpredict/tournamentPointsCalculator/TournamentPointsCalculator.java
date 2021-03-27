package com.pdict.iplpredict.tournamentPointsCalculator;

import com.pdict.iplpredict.entities.Tournament;
import com.pdict.iplpredict.entities.UserTournamentPrediction;

import java.util.ArrayList;
import java.util.List;

public class TournamentPointsCalculator {
    List<TournamentPointsPolicy> tournamentPointsPolicies = new ArrayList<>();

    public TournamentPointsCalculator() {
        tournamentPointsPolicies.add(new OrangeCapPolicy());
        tournamentPointsPolicies.add(new PurpleCapPolicy());
        tournamentPointsPolicies.add(new RunnerupPolicy());
        tournamentPointsPolicies.add(new TopFourPolicy());
        tournamentPointsPolicies.add(new WinnerPolicy());
        tournamentPointsPolicies.add(new BonusPolicy());
    }

    public Integer getTournamentPoints(UserTournamentPrediction userTournamentPrediction, Tournament tournament) {
        Integer points = 0;

        for(TournamentPointsPolicy tournamentPointsPolicy : tournamentPointsPolicies) {
            if(tournamentPointsPolicy.isApplicable(userTournamentPrediction , tournament)) {
                points += tournamentPointsPolicy.getPoints(userTournamentPrediction , tournament);
            }
        }

        return points;
    }
}
