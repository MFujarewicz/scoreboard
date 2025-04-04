package org.scoreboard;

public record Match(String homeTeam, String awayTeam, int homeTeamScore, int awayTeamScore) {
    public int totalScore() {
        return homeTeamScore + awayTeamScore;
    }
}
