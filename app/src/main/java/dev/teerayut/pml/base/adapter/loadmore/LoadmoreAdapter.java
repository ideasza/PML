package dev.teerayut.pml.base.adapter.loadmore;

import android.view.ViewGroup;


import java.util.List;

import dev.teerayut.pml.base.adapter.BaseItem;
import dev.teerayut.pml.base.adapter.BaseItemType;
import dev.teerayut.pml.base.adapter.BaseMvpListAdapter;
import dev.teerayut.pml.base.adapter.BaseViewHolder;
import dev.teerayut.pml.base.adapter.progress.ProgressHolder;


/**
 * Created by thekhaeng on 4/24/2017 AD.
 */

public abstract class LoadmoreAdapter<VH extends BaseViewHolder, P extends LoadmoreAdapterInterface.Presenter>
        extends BaseMvpListAdapter<VH, P>
        implements LoadmoreAdapterInterface.Adapter{

    private OnLoadMoreListener loadMoreListener;

    public void setOnLoadMoreListener( OnLoadMoreListener listener ){
        this.loadMoreListener = listener;
    }

    public void setItems(List<BaseItem> items, boolean isNextItemAvailable ){
        getPresenter().setItems( items, isNextItemAvailable );
    }

    @SuppressWarnings( "unchecked" )
    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType ){
        if( viewType == BaseItemType.TYPE_PROGRESS ){
            return (VH) new ProgressHolder( parent );
        }
        return null;
    }

    @Override
    public void onBindViewHolder( VH holder, int position ){
        if( getItemViewType( position ) == BaseItemType.TYPE_PROGRESS ){
            if( loadMoreListener != null ){
                loadMoreListener.onLoadMore();
            }
        }
    }
}
