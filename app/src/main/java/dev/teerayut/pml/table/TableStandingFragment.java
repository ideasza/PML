package dev.teerayut.pml.table;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import dev.teerayut.pml.R;
import dev.teerayut.pml.base.BaseMvpFragment;
import dev.teerayut.pml.main.MainActivity;
import dev.teerayut.pml.table.adapter.TableStandingsAdapter;
import dev.teerayut.pml.table.item.StandingItem;
import dev.teerayut.pml.table.item.StandingItemGroup;
import dev.teerayut.pml.utils.Config;

/**
 * A simple {@link Fragment} subclass.
 */
public class TableStandingFragment extends BaseMvpFragment<TableStandingInterface.Presenter>
        implements TableStandingInterface.View {
    private TableStandingsAdapter adapter;
    private List<StandingItem> standingItems = new ArrayList<StandingItem>();

    public static TableStandingFragment getInstance(){
        return new TableStandingFragment();
    }

    @Override
    public TableStandingInterface.Presenter createPresenter() {
        return TableStandingPresenter.create();
    }

    @Override
    public int getLayoutView() {
        return R.layout.fragment_table_standing;
    }


    @Bind(R.id.recyclerView) RecyclerView recyclerView;
    @Bind(R.id.swipeRefreshLayout) SwipeRefreshLayout swipeRefreshLayout;
    @Override
    public void bindView(View view) {
        ButterKnife.bind(this, view);
    }

    @Override
    public void setupInstance() {
        adapter = new TableStandingsAdapter(getActivity());
    }

    @Override
    public void setupView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                getPresenter().requestStandingTable();
            }
        });
    }

    @Override
    public void initialize() {
        getPresenter().requestStandingTable();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable( Config.KEY_STANDING_TABLE, getPresenter().getStandingGroup() );
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        getPresenter().setStandingGroup( (StandingItemGroup) savedInstanceState.getParcelable( Config.KEY_STANDING_TABLE ) );
    }

    @Override
    public void restoreView(Bundle savedInstanceState) {
        super.restoreView(savedInstanceState);
        getPresenter().setStandingToAdapter(getPresenter().getStandingGroup());
    }

    @Override
    public void onLoad() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void onDismiss() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void setStandingTableToAdapter(List<StandingItem> standingItemList) {
        setToolbar();
        adapter.setStandingItemList(standingItemList);
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout.setRefreshing(false);
    }

    private void setToolbar() {
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.setToolbar(dev.teerayut.pml.utils.MyApplication.getInstance().getPrefManager().getPreferrence(Config.TITLE_TOOLBAR));
    }

    public void detectWifiConnected(final String state) {
        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                if (state.equals("connect")){
                    swipeRefreshLayout.setRefreshing(true);
                    getPresenter().requestStandingTable();
                } else {
                    /*mRecyclerView.setVisibility(View.GONE);
                    relativeLayoutConnectionError.setVisibility(View.VISIBLE);*/
                }
            }
        });
    }
}
