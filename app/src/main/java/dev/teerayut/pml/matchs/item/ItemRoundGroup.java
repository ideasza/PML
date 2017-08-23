package dev.teerayut.pml.matchs.item;

import android.os.Parcel;
import android.os.Parcelable;


import java.util.ArrayList;
import java.util.List;

import dev.teerayut.pml.base.adapter.BaseItem;

/**
 * Created by teerayut.k on 8/5/2017.
 */

public class ItemRoundGroup implements Parcelable {

    private List<ItemRound> fixtures;

    public List<ItemRound> getFixtures() {
        return fixtures;
    }

    public void setFixtures(List<ItemRound> fixtures) {
        this.fixtures = fixtures;
    }

    public ItemRoundGroup(Parcel in) {
        fixtures = in.createTypedArrayList(ItemRound.CREATOR);
    }

    public static final Creator<ItemRoundGroup> CREATOR = new Creator<ItemRoundGroup>() {
        @Override
        public ItemRoundGroup createFromParcel(Parcel in) {
            return new ItemRoundGroup(in);
        }

        @Override
        public ItemRoundGroup[] newArray(int size) {
            return new ItemRoundGroup[size];
        }
    };

    public ItemRoundGroup() {

    }

    public List<BaseItem> getBaseItems(){
        List<BaseItem> baseItems = new ArrayList<>(  );
        for( ItemRound item : fixtures ){
            baseItems.add(item);
        }
        return baseItems;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(fixtures);
    }
}
