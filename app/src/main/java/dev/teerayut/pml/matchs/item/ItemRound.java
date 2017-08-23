package dev.teerayut.pml.matchs.item;

import android.os.Parcel;
import android.os.Parcelable;


import java.util.List;

import dev.teerayut.pml.base.adapter.BaseItem;

/**
 * Created by teera-s on 8/30/2016 AD.
 */
public class ItemRound extends BaseItem implements Parcelable {

    private String date;
    private String status;
    private String matchday;
    private String homeTeamName;
    private String awayTeamName;
    private result result;

    public ItemRound() {

    }

    public String getDate() {
        return date;
    }

    public ItemRound setDate(String date) {
        this.date = date;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public ItemRound setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getMatchday() {
        return matchday;
    }

    public ItemRound setMatchday(String matchday) {
        this.matchday = matchday;
        return this;
    }

    public String getHomeTeamName() {
        return homeTeamName;
    }

    public ItemRound setHomeTeamName(String homeTeamName) {
        this.homeTeamName = homeTeamName;
        return this;
    }

    public String getAwayTeamName() {
        return awayTeamName;
    }

    public ItemRound setAwayTeamName(String awayTeamName) {
        this.awayTeamName = awayTeamName;
        return this;
    }

    public result getResult() {
        return result;
    }

    public ItemRound setResult(result result) {
        this.result = result;
        return this;
    }

    public ItemRound(Parcel in) {
        super(in);
        date = in.readString();
        status = in.readString();
        matchday = in.readString();
        homeTeamName = in.readString();
        awayTeamName = in.readString();
        //result = in.createTypedArrayList(com.teerayut.soccerleague.v2.matchs.item.result.CREATOR);
        //result = in.re
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(date);
        dest.writeString(status);
        dest.writeString(matchday);
        dest.writeString(homeTeamName);
        dest.writeString(awayTeamName);
        //dest.writeValue(result);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ItemRound> CREATOR = new Creator<ItemRound>() {
        @Override
        public ItemRound createFromParcel(Parcel in) {
            return new ItemRound(in);
        }

        @Override
        public ItemRound[] newArray(int size) {
            return new ItemRound[size];
        }
    };
}
