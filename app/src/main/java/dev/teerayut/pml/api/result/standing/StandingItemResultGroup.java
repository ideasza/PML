package dev.teerayut.pml.api.result.standing;


import java.util.List;

/**
 * Created by teerayut.k on 7/24/2017.
 */

public class StandingItemResultGroup {

    private String leagueCaption;
    private List<StandingItemResult> standing;

    public String getLeagueCaption() {
        return leagueCaption;
    }

    public void setLeagueCaption(String leagueCaption) {
        this.leagueCaption = leagueCaption;
    }

    public List<StandingItemResult> getStanding() {
        return standing;
    }

    public void setStanding(List<StandingItemResult> standing) {
        this.standing = standing;
    }
}
