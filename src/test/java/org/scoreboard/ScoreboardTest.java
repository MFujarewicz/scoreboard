package org.scoreboard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreboardTest {

    @Test
    void startMatchReturnSingleItem() {

        Scoreboard scoreboard = new Scoreboard();

        String homeTeam = "Mexico";
        String awayTeam = "Canada";

        scoreboard.startMatch(homeTeam, awayTeam);

        assertEquals(1, scoreboard.getSummary().size(), "Summary should contain one match");


        assertEquals(
                new Match(homeTeam, awayTeam, 0, 0),
                scoreboard.getSummary().getFirst(),
                "Match should have correct home and away teams, and be initialized to 0 - 0 score");
    }
}