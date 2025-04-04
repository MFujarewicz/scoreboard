package org.scoreboard;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Scoreboard {
    ArrayList<Match> matches;

    public Scoreboard() {
        matches = new ArrayList<Match>();
    }

    public void setScore(String homeTeam, String awayTeam, int newHomeTeamScore, int newAwayTeamScore) {
        for (int i = 0; i < matches.size(); i++){
            Match match = matches.get(i);
            if (match.homeTeam().equals(homeTeam) && match.awayTeam().equals(awayTeam)){
                matches.set(i, new Match(homeTeam, awayTeam, newHomeTeamScore, newAwayTeamScore));
            }

        }
    }

    public void startMatch(String homeTeam, String awayTeam) {
        matches.add(
                new Match(homeTeam, awayTeam, 0, 0)
        );
    }

    public List<Match> getSummary() {
        var matchesToSort = new ArrayList<>(matches);
        matchesToSort.sort(Comparator.comparing(Match::totalScore));
        return matchesToSort.reversed();

    }
}


