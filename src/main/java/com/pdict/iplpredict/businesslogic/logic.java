package com.pdict.iplpredict.businesslogic;

import com.pdict.iplpredict.database.*;
import com.pdict.iplpredict.entities.*;

import java.util.*;
import java.lang.Math.*;

public class logic {
    public Integer matchwisePointsCalculator(User user , Match match){
        Integer points=0;
        MatchwisePrediction matchwisePrediction = MatchwisePredictionRepository.getMatchwisePredictionByMatchIdAndUsername(user.userName, match.matchId);
        if (matchwisePrediction.teamWin == match.teamWin){
            points+=10;
            if (match.teamWin == user.favTeam){
                points+=2;
            }
        }

        if (match.teamScore1 >= matchwisePrediction.teamLow1 && match.teamScore1 <= matchwisePrediction.teamHigh1){
            points+=5;
        }
        if (match.teamScore2 >= matchwisePrediction.teamLow2 && match.teamScore2 <= matchwisePrediction.teamHigh2){
            points+=5;
        }

        if (Math.abs(match.wickets - matchwisePrediction.wickets)<=1){
            points+=10;
        }
        return points;
    }

    public int tournamentPointsCalculator(User user , TournamentResult tournamentResult){
        Integer points=0;
        Prediction prediction = PredictionRepository.getPrediction(user.userName , tournamentResult.tournamentYear);
        if (prediction.firstTeam == tournamentResult.firstTeam ){
            points+=80;
            if (prediction.firstTeam == user.favTeam){
                points+=20;
            }
        }
        if (prediction.firstTeam == tournamentResult.secondTeam ){
            points+=50;
            if (prediction.firstTeam == user.favTeam){
                points+=20;
            }
        }
        if (prediction.orangeCap1 == tournamentResult.orangeCap || prediction.orangeCap2 == tournamentResult.orangeCap || prediction.orangeCap3 == tournamentResult.orangeCap){
            points+=50;
        }
        if (prediction.purpleCap1 == tournamentResult.purpleCap || prediction.purpleCap2 == tournamentResult.purpleCap || prediction.purpleCap3 == tournamentResult.purpleCap){
            points+=50;
        }

        Set <String> s = new HashSet<String> ();
        s.add(tournamentResult.firstTeam);
        s.add(tournamentResult.secondTeam);
        s.add(tournamentResult.thirdTeam);
        s.add(tournamentResult.fourthTeam);
        Integer semiPoints=0;
        if (s.contains(prediction.firstTeam)){
            semiPoints+=10;
        }
        if (s.contains(prediction.secondTeam)){
            semiPoints+=10;
        }
        if (s.contains(prediction.thirdTeam)){
            semiPoints+=10;
        }
        if (s.contains(prediction.fourthTeam)){
            semiPoints+=10;
        }
        if (semiPoints==40){
            semiPoints=50;
        }
        points+=semiPoints;

        return points;

    }
}
