package org.scoreboard;

import java.util.ArrayList;
import java.util.List;

public class Scoreboard {
    ArrayList<Match> matches;

    public Scoreboard() {
        matches = new ArrayList<Match>();
    }


    public void startMatch(String homeTeam, String awayTeam) {
        matches.add(
                new Match(homeTeam, awayTeam, 0, 0)
        );
    }

    public List<Match> getSummary() {
        return matches;
    }
}


