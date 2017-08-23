package dev.teerayut.pml.matchs;


import java.util.List;

import dev.teerayut.pml.base.BaseMvpInterface;
import dev.teerayut.pml.matchs.item.ItemRound;
import dev.teerayut.pml.matchs.item.ItemRoundGroup;

/**
 * Created by teerayut.k on 8/5/2017.
 */

public class RoundsInterface {

    public interface View extends BaseMvpInterface.View {
        void onLoad();
        void onDimiss();
        void setMatchItemToAdapter(List<ItemRound> itemRoundList);
    }

    public interface Presenter extends BaseMvpInterface.Presenter<View> {
        void requestFixtures();
        ItemRoundGroup getMatchGroup();
        void setMatchGroup(ItemRoundGroup itemRoundGroup);
        void setMatchToAdapter(ItemRoundGroup itemRoundGroup);
    }
}
