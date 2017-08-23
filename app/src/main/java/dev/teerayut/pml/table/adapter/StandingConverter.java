package dev.teerayut.pml.table.adapter;


import java.util.ArrayList;
import java.util.List;

import dev.teerayut.pml.api.result.standing.StandingItemResult;
import dev.teerayut.pml.api.result.standing.StandingItemResultGroup;
import dev.teerayut.pml.table.item.StandingItem;
import dev.teerayut.pml.table.item.StandingItemGroup;

/**
 * Created by teerayut.k on 7/24/2017.
 */

public class StandingConverter {

    public static StandingItemGroup createStandingGroupItemFromResult(StandingItemResultGroup result ){
        StandingItemGroup group = new StandingItemGroup();
        group.setLeagueCaption( result.getLeagueCaption() );
        group.setStanding( StandingConverter.createStandingItemsFromResult( result.getStanding() ) );
        return group;
    }

    public static List<StandingItem> createStandingItemsFromResult(List<StandingItemResult> result){
        List<StandingItem> items = new ArrayList<>();
        for( StandingItemResult standingItemResult : result ){
            StandingItem item = new StandingItem()
                    .setPosition(standingItemResult.getPosition())
                    .setTeamName(standingItemResult.getTeamName())
                    .setCrestURI(standingItemResult.getCrestURI())
                    .setPlayedGames(standingItemResult.getPlayedGames())
                    .setPoints(standingItemResult.getPoints())
                    .setGoals(standingItemResult.getGoals())
                    .setGoalsAgainst(standingItemResult.getGoalsAgainst())
                    .setGoalDifference(standingItemResult.getGoalDifference())
                    .setWins(standingItemResult.getWins())
                    .setDraws(standingItemResult.getDraws())
                    .setLosses(standingItemResult.getLosses());
            items.add( item );
        }
        return items;
    }
}
