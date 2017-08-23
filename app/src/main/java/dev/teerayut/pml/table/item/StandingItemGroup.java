package dev.teerayut.pml.table.item;

import android.os.Parcel;
import android.os.Parcelable;


import java.util.ArrayList;
import java.util.List;

import dev.teerayut.pml.base.adapter.BaseItem;

/**
 * Created by teerayut.k on 7/24/2017.
 */

public class StandingItemGroup implements Parcelable  {

    private String leagueCaption;
    private List<StandingItem> standing;

    public StandingItemGroup() {

    }

    public List<StandingItem> getStanding() {
        return standing;
    }

    public void setStanding(List<StandingItem> standing) {
        this.standing = standing;
    }

    public String getLeagueCaption() {
        return leagueCaption;
    }

    public void setLeagueCaption(String leagueCaption) {
        this.leagueCaption = leagueCaption;
    }

    public List<BaseItem> getBaseItems(){
        List<BaseItem> baseItems = new ArrayList<>(  );
        for( StandingItem item : standing ){
            baseItems.add(item);
        }
        return baseItems;
    }

    public StandingItemGroup(Parcel in) {
        leagueCaption = in.readString();
        standing = in.createTypedArrayList(StandingItem.CREATOR);
    }

    public static final Creator<StandingItemGroup> CREATOR = new Creator<StandingItemGroup>() {
        @Override
        public StandingItemGroup createFromParcel(Parcel in) {
            return new StandingItemGroup(in);
        }

        @Override
        public StandingItemGroup[] newArray(int size) {
            return new StandingItemGroup[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(leagueCaption);
        parcel.writeTypedList(standing);
    }
}
