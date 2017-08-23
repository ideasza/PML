package dev.teerayut.pml.matchs;

import android.util.Log;

import com.hwangjr.rxbus.RxBus;

import java.util.ArrayList;
import java.util.List;

import dev.teerayut.pml.api.ServiceManager;
import dev.teerayut.pml.api.result.match.MatchItemResultGroup;
import dev.teerayut.pml.base.BaseMvpPresenter;
import dev.teerayut.pml.matchs.adapter.MatchConverter;
import dev.teerayut.pml.matchs.item.ItemRound;
import dev.teerayut.pml.matchs.item.ItemRoundGroup;

/**
 * Created by teerayut.k on 8/5/2017.
 */

public class RoundsPresenter extends BaseMvpPresenter<RoundsInterface.View> implements RoundsInterface.Presenter {

    private ServiceManager serviceManager;
    private ItemRoundGroup itemRoundGroup;
    private List<ItemRound> itemRounds = new ArrayList<ItemRound>();
    public static RoundsInterface.Presenter create() {
        return new RoundsPresenter();
    }

    public RoundsPresenter() {
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
    public void requestFixtures() {
        Log.e("Request Fixture", "Request");
        getView().onLoad();
        serviceManager.requestFixtures(new ServiceManager.ServiceManagerCallback<MatchItemResultGroup>() {
            @Override
            public void onSuccess(MatchItemResultGroup result) {
                ItemRoundGroup resultGroup = MatchConverter.createMatchGroupItemFromResult(result);
                itemRoundGroup = resultGroup;
                setMatchToAdapter(itemRoundGroup);

                itemRounds.clear();
                itemRounds = MatchConverter.createMatchGroupItemFromResult(result).getFixtures();
                getView().setMatchItemToAdapter(itemRounds);
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("Request Fixture failed", t.getMessage());
                getView().onDimiss();
            }
        });

    }

    @Override
    public ItemRoundGroup getMatchGroup() {
        return itemRoundGroup;
    }

    @Override
    public void setMatchGroup(ItemRoundGroup itemRoundGroup) {
        this.itemRoundGroup = itemRoundGroup;
    }

    @Override
    public void setMatchToAdapter(ItemRoundGroup itemRoundGroup) {
    }
}
