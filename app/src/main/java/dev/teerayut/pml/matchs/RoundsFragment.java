package dev.teerayut.pml.matchs;


import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import dev.teerayut.pml.R;
import dev.teerayut.pml.base.BaseMvpFragment;
import dev.teerayut.pml.matchs.adapter.RoundsAdapter;
import dev.teerayut.pml.matchs.item.ItemRound;
import dev.teerayut.pml.matchs.item.ItemRoundGroup;
import dev.teerayut.pml.utils.Config;

/**
 * A simple {@link Fragment} subclass.
 */
public class RoundsFragment extends BaseMvpFragment<RoundsInterface.Presenter> implements RoundsInterface.View {


    public RoundsFragment() {
        // Required empty public constructor
    }

    private RoundsAdapter adapter;
    private List<ItemRound> itemRounds = new ArrayList<ItemRound>();

    public static RoundsFragment getInstance(){
        return new RoundsFragment();
    }

    @Override
    public RoundsInterface.Presenter createPresenter() {
        return RoundsPresenter.create();
    }

    @Override
    public int getLayoutView() {
        return R.layout.fragment_match_fixtures;
    }

    @Bind(R.id.fab)
    FloatingActionButton refresh;
    @Bind(R.id.recyclerViewAllMatch) RecyclerView recyclerView;
    @Bind(R.id.swipeRefreshLayoutAllMatch) SwipeRefreshLayout swipeRefreshLayout;
    @Override
    public void bindView(View view) {
        ButterKnife.bind(this, view);
        refresh.setOnClickListener( onRefresh() );
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable( Config.KEY_FIXTURES, getPresenter().getMatchGroup() );
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        getPresenter().setMatchGroup( (ItemRoundGroup) savedInstanceState.getParcelable( Config.KEY_FIXTURES ) );
    }

    @Override
    public void restoreView(Bundle savedInstanceState) {
        super.restoreView(savedInstanceState);
        getPresenter().setMatchToAdapter(getPresenter().getMatchGroup());
    }

    @Override
    public void setupInstance() {
    }

    @Override
    public void setupView() {
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                getPresenter().requestFixtures();
            }
        });
    }

    @Override
    public void initialize() {
        getPresenter().requestFixtures();
    }

    @Override
    public void onLoad() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void onDimiss() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void setMatchItemToAdapter(List<ItemRound> itemRoundList) {
        this.itemRounds = itemRoundList;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new RoundsAdapter(getActivity(), itemRoundList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);

        for (int i = 0; i < itemRoundList.size(); i++) {
            if (getCurrentDate().equals(splitDate(itemRoundList.get(i).getDate().replace("T", " ").replace("Z", "")))) {
                recyclerView.getLayoutManager().scrollToPosition(i-1);
            }
        }

    }

    private String splitDate(String str) {
        String[] d = str.split(" ");
        return d[0];
    }

    private String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDateandTime = sdf.format(new Date());
        return currentDateandTime;
    }

    public void detectWifiConnected(final String state) {
        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                if (state.equals("connect")){
                    swipeRefreshLayout.setRefreshing(true);
                    getPresenter().requestFixtures();
                } else {
                    /*mRecyclerView.setVisibility(View.GONE);
                    relativeLayoutConnectionError.setVisibility(View.VISIBLE);*/
                }
            }
        });
    }

    private View.OnClickListener onRefresh() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                swipeRefreshLayout.setRefreshing(true);
                getPresenter().requestFixtures();
            }
        };
    }
}
