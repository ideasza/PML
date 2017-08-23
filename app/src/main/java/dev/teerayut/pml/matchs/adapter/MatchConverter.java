package dev.teerayut.pml.matchs.adapter;


import java.util.ArrayList;
import java.util.List;

import dev.teerayut.pml.api.result.match.MatchItemResult;
import dev.teerayut.pml.api.result.match.MatchItemResultGroup;
import dev.teerayut.pml.matchs.item.ItemRound;
import dev.teerayut.pml.matchs.item.ItemRoundGroup;

/**
 * Created by teerayut.k on 8/5/2017.
 */

public class MatchConverter {

    public static ItemRoundGroup createMatchGroupItemFromResult(MatchItemResultGroup result ){
        ItemRoundGroup group = new ItemRoundGroup();
        group.setFixtures(MatchConverter.createMatchItemsFromResult( result.getFixtures()));
        return group;
    }

    public static List<ItemRound> createMatchItemsFromResult(List<MatchItemResult> result){
        List<ItemRound> items = new ArrayList<>();
        for( MatchItemResult matchItemResult : result ){
            ItemRound item = new ItemRound()
                    .setDate(matchItemResult.getDate())
                    .setStatus(matchItemResult.getStatus())
                    .setMatchday(matchItemResult.getMatchday())
                    .setHomeTeamName(matchItemResult.getHomeTeamName())
                    .setAwayTeamName(matchItemResult.getAwayTeamName())
                    .setResult(matchItemResult.getResult());
            items.add( item );
        }
        return items;
    }
}
