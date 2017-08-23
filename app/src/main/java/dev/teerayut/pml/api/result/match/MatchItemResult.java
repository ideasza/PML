package dev.teerayut.pml.api.result.match;


import java.util.List;

import dev.teerayut.pml.matchs.item.result;

/**
 * Created by teerayut.k on 8/5/2017.
 */

public class MatchItemResult {

    private String date;
    private String status;
    private String matchday;
    private String homeTeamName;
    private String awayTeamName;
    private dev.teerayut.pml.matchs.item.result result;

    public dev.teerayut.pml.matchs.item.result getResult() {
        return result;
    }

    public void setResult(result result) {
        this.result = result;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMatchday() {
        return matchday;
    }

    public void setMatchday(String matchday) {
        this.matchday = matchday;
    }

    public String getHomeTeamName() {
        return homeTeamName;
    }

    public void setHomeTeamName(String homeTeamName) {
        this.homeTeamName = homeTeamName;
    }

    public String getAwayTeamName() {
        return awayTeamName;
    }

    public void setAwayTeamName(String awayTeamName) {
        this.awayTeamName = awayTeamName;
    }

}
