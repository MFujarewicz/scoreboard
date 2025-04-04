Lib implementing scoreboard
    

### Usage
entry point is creating new scoreboard:
```
Scoreboard scoreboard = new Scoreboard();
```


to add match 
```
scoreboard.startMatch("homeTeamName", "awayTeamName");
```

then you can set the scores:

```
scoreboard.setScore("homeTeamName", "awayTeamName", 1, 2);
```

when you no longer want match to be included in the scoreboard you can end it (score will be lost!).

```
scoreboard.endMatch("homeTeamName", "awayTeamName");
```

at any point you can get summary of all non-ended matches, returned as List of Matches ordered by total score descending and start time descending.

```
scoreboard.getSummary(); // List<Match> (Match("team1", "team2", 1, 2), Match(...),...)
```



### to run tests (you'll need maven installed): 
```
mvn test
```

