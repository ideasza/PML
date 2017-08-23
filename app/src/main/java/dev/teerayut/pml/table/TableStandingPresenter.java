package dev.teerayut.pml.table;

import android.util.Log;

import com.hwangjr.rxbus.RxBus;
import java.util.ArrayList;
import java.util.List;

import dev.teerayut.pml.api.ServiceManager;
import dev.teerayut.pml.api.result.standing.StandingItemResultGroup;
import dev.teerayut.pml.base.BaseMvpPresenter;
import dev.teerayut.pml.table.adapter.StandingConverter;
import dev.teerayut.pml.table.item.StandingItem;
import dev.teerayut.pml.table.item.StandingItemGroup;
import dev.teerayut.pml.utils.Config;
import dev.teerayut.pml.utils.MyApplication;

/**
 * Created by teerayut.k on 7/24/2017.
 */

public class TableStandingPresenter extends BaseMvpPresenter<TableStandingInterface.View> implements TableStandingInterface.Presenter {

    private ServiceManager serviceManager;
    private StandingItemGroup standingItemGroup;
    private List<StandingItem> standingItems = new ArrayList<StandingItem>();

    public static TableStandingInterface.Presenter create(){
        return new TableStandingPresenter();
    }

    public TableStandingPresenter() {
        serviceManager = ServiceManager.getInstance();
    }

    public void setManager( ServiceManager manager ){
        serviceManager = manager;
    }

    @Override
    public void onViewCreate(){
        RxBus.get().register( this );
    }

    @Override
    public void onViewDestroy(){
        RxBus.get().unregister( this );
    }

    @Override
    public void requestStandingTable() {
        getView().onLoad();
        serviceManager.requestStandingTable(new ServiceManager.ServiceManagerCallback<StandingItemResultGroup>() {
            @Override
            public void onSuccess(StandingItemResultGroup result) {
                StandingItemGroup resultGroup = StandingConverter.createStandingGroupItemFromResult(result);
                Log.e("LeagueCaption", resultGroup.getLeagueCaption());
                standingItemGroup = resultGroup;
                setStandingToAdapter(standingItemGroup);
                MyApplication.getInstance().getPrefManager().setPreferrence(Config.TITLE_TOOLBAR, resultGroup.getLeagueCaption());

                standingItems.clear();
                standingItems = StandingConverter.createStandingGroupItemFromResult(result).getStanding();
                getView().setStandingTableToAdapter(standingItems);
            }

            @Override
            public void onFailure(Throwable t) {
                getView().onDismiss();
            }
        });
    }

    @Override
    public StandingItemGroup getStandingGroup() {
        return standingItemGroup;
    }

    @Override
    public void setStandingGroup(StandingItemGroup standingGroup) {
        this.standingItemGroup = standingGroup;
    }

    @Override
    public void setStandingToAdapter(StandingItemGroup standingGroup) {
        getView().setStandingTableToAdapter(standingGroup.getStanding());
    }
}
