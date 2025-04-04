package org.scoreboard;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Scoreboard {
    ArrayList<Match> matches;

    public Scoreboard() {
        matches = new ArrayList<Match>();
    }


    /**
     * Set score for an existing match. before setting score match must be started using startMatch
     *
     * @param homeTeam home team name
     * @param awayTeam away team name
     * @param newHomeTeamScore score for home team
     * @param newAwayTeamScore score for away team
     * @throws IllegalArgumentException if no match exists for specified teams
     */
    public void setScore(String homeTeam, String awayTeam, int newHomeTeamScore, int newAwayTeamScore) {
        int index = findIndexOrThrow(homeTeam, awayTeam);
        matches.set(index, new Match(homeTeam, awayTeam, newHomeTeamScore, newAwayTeamScore));
    }

    /**
     * End match adn remove it from scoreboard.
     *
     * @param homeTeam home team name
     * @param awayTeam away team name
     * @throws IllegalArgumentException if no match exists for specified teams
     */
    public void endMatch(String homeTeam, String awayTeam) {
        matches.remove(findIndexOrThrow(homeTeam, awayTeam));
    }


    /**
     * Add new match to scoreboard with score 0-0
     *
     * @param homeTeam home team name
     * @param awayTeam away team name
     * @throws IllegalArgumentException if a match for specified teams is already on the scoreboard
     */
    public void startMatch(String homeTeam, String awayTeam) {
        if (findIndex(homeTeam, awayTeam) != -1) throw new IllegalArgumentException("Match between " + homeTeam + " and " + awayTeam + " is already on scoreboard");

        matches.add(
                new Match(homeTeam, awayTeam, 0, 0)
        );
    }


    /**
     * Get summary of all matches, sored by total score descending and recency newest to oldest
     * @return ordered list of matches
     */
    public List<Match> getSummary() {
        //order by total score is coded explicitly, secondary order by recency is achieved by preserving order in which
        //matches were started in internal arrayList, using stable sort and reversing array at the end so most recent are first.
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


