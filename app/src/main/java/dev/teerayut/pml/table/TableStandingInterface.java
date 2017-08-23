package dev.teerayut.pml.table;


import java.util.List;

import dev.teerayut.pml.base.BaseMvpInterface;
import dev.teerayut.pml.table.item.StandingItem;
import dev.teerayut.pml.table.item.StandingItemGroup;

/**
 * Created by teerayut.k on 7/24/2017.
 */

public class TableStandingInterface {

    public interface View extends BaseMvpInterface.View{
        void onLoad();
        void onDismiss();
        void setStandingTableToAdapter(List<StandingItem> standingItemList);
    }

    public interface Presenter extends  BaseMvpInterface.Presenter<View>{
        void requestStandingTable();

        StandingItemGroup getStandingGroup();
        void setStandingGroup(StandingItemGroup standingGroup);
        void setStandingToAdapter(StandingItemGroup standingGroup);
    }
}
