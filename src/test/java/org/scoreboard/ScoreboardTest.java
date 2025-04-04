package org.scoreboard;

import org.junit.jupiter.api.Test;

import java.util.List;

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

    @Test
    void setScoreTest() {

        Scoreboard scoreboard = new Scoreboard();

        String homeTeam = "Mexico";
        String awayTeam = "Canada";

        scoreboard.startMatch(homeTeam, awayTeam);

        scoreboard.setScore(homeTeam, awayTeam, 1, 2);

        assertEquals(
                new Match(homeTeam, awayTeam, 1, 2),
                scoreboard.getSummary().getFirst(),
                "Match should have updated score");
    }

    @Test
    void summaryOrderTest() {

        Scoreboard scoreboard = new Scoreboard();

        String match1HT = "Mexico";
        String match1AT = "Canada";

        String match2HT = "Spain";
        String match2AT = "Brazil";

        String match3HT = "Germany";
        String match3AT = "France";

        scoreboard.startMatch(match1HT, match1AT);
        scoreboard.startMatch(match2HT, match2AT);
        scoreboard.startMatch(match3HT, match3AT);

        scoreboard.setScore(match1HT, match1AT, 1, 1);
        scoreboard.setScore(match2HT, match2AT, 3, 3);
        scoreboard.setScore(match3HT, match3AT, 2, 2);

        assertArrayEquals(List.of(
                        new Match(match2HT, match2AT, 3, 3),
                        new Match(match3HT, match3AT, 2, 2),
                        new Match(match1HT, match1AT, 1, 1)
                ).toArray(new Match[0]),
                scoreboard.getSummary().toArray(),
                "Summary should be ordered by total score descending");


        scoreboard.setScore(match3HT, match3AT, 1, 1);

        assertArrayEquals(List.of(
                        new Match(match2HT, match2AT, 3, 3),
                        new Match(match3HT, match3AT, 1, 1),
                        new Match(match1HT, match1AT, 1, 1)
                ).toArray(new Match[0]),
                scoreboard.getSummary().toArray(),
                "The matches with the same total score should be returned ordered by the most recently started match");
    }

    @Test
    void testWithMoreMatches() {

        Scoreboard scoreboard = new Scoreboard();


        String mexico = "Mexico";
        String canada = "Canada";
        String spain = "Spain";
        String brazil = "Brazil";
        String germany = "Germany";
        String france = "France";
        String uruguay = "Uruguay";
        String italy = "Italy";
        String argentina = "Argentina";
        String australia = "Australia";

        scoreboard.startMatch(mexico, canada);
        scoreboard.setScore(mexico, canada, 0, 5);

        scoreboard.startMatch(spain, brazil);
        scoreboard.setScore(spain, brazil, 10, 2);

        scoreboard.startMatch(germany, france);
        scoreboard.setScore(germany, france, 2, 2);

        scoreboard.startMatch(uruguay, italy);
        scoreboard.setScore(uruguay, italy, 6, 6);

        scoreboard.startMatch(argentina, australia);
        scoreboard.setScore(argentina, australia, 3, 1);


        assertArrayEquals(List.of(
                        new Match(uruguay, italy, 6, 6),
                        new Match(spain, brazil, 10, 2),
                        new Match(mexico, canada, 0, 5),
                        new Match(argentina, australia, 3, 1),
                        new Match(germany, france, 2, 2)
                ).toArray(new Match[0]),
                scoreboard.getSummary().toArray(),
                "Summary should be ordered by total score and recency");


    }

    @Test
    void endMatchTest() {

        Scoreboard scoreboard = new Scoreboard();

        String match1HT = "Mexico";
        String match1AT = "Canada";

        String match2HT = "Spain";
        String match2AT = "Brazil";

        String match3HT = "Germany";
        String match3AT = "France";

        scoreboard.startMatch(match1HT, match1AT);
        scoreboard.startMatch(match2HT, match2AT);
        scoreboard.startMatch(match3HT, match3AT);

        scoreboard.setScore(match2HT, match2AT, 1, 2);

        scoreboard.endMatch(match1HT, match1AT);
        scoreboard.endMatch(match3HT, match3AT);


        assertArrayEquals(List.of(
                        new Match(match2HT, match2AT, 1, 2)
                ).toArray(new Match[0]),
                scoreboard.getSummary().toArray(),
                "Summary should include only one last non removed match");

        scoreboard.endMatch(match2HT, match2AT);
        assertEquals(0, scoreboard.getSummary().size(), "all matches should be removed from scoreboard");
    }

    @Test
    void duplicateMatchTest() {

        Scoreboard scoreboard = new Scoreboard();

        String mexico = "Mexico";
        String canada = "Canada";

        scoreboard.startMatch(mexico, canada);

        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    scoreboard.startMatch(mexico, canada);
                },
                "exception should be thrown when starting duplicate match"
        );

        scoreboard.setScore(mexico, canada, 1, 2);

        assertEquals(1, scoreboard.getSummary().size(), "Summary should include one match");
    }


}