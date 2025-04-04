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
        int index = findIndexOrThrow(homeTeam, awayTeam);
        matches.set(index, new Match(homeTeam, awayTeam, newHomeTeamScore, newAwayTeamScore));
    }

    public void endMatch(String homeTeam, String awayTeam) {
        matches.remove(findIndexOrThrow(homeTeam, awayTeam));
    }

    public void startMatch(String homeTeam, String awayTeam) {
        if (findIndex(homeTeam, awayTeam) != -1) throw new IllegalArgumentException("Match between " + homeTeam + " and " + awayTeam + " is already on scoreboard");

        matches.add(
                new Match(homeTeam, awayTeam, 0, 0)
        );
    }

    public List<Match> getSummary() {
        var matchesToSort = new ArrayList<>(matches);
        matchesToSort.sort(Comparator.comparing(Match::totalScore));
        return matchesToSort.reversed();

    }

    private int findIndex(String homeTeam, String awayTeam){
        for (int i = 0; i < matches.size(); i++){
            Match match = matches.get(i);
            if (match.homeTeam().equals(homeTeam) && match.awayTeam().equals(awayTeam)){
                return i;
            }
        }
        return -1;
    }

    private int findIndexOrThrow(String homeTeam, String awayTeam){
        int index = findIndex(homeTeam, awayTeam);

        if (index != -1)
            return index;
        else
            throw new IllegalArgumentException("No active match for home team: " + homeTeam + ", away team: " + awayTeam);
    }
}


