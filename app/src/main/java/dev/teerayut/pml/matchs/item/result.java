package dev.teerayut.pml.matchs.item;

import android.os.Parcel;
import android.os.Parcelable;

import dev.teerayut.pml.base.adapter.BaseItem;


/**
 * Created by teerayut.k on 8/5/2017.
 */

public class result extends BaseItem implements Parcelable {

    private int goalsHomeTeam;
    private int goalsAwayTeam;

    protected result(Parcel in) {
        super(in);
        goalsHomeTeam = in.readInt();
        goalsAwayTeam = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(goalsHomeTeam);
        dest.writeInt(goalsAwayTeam);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<result> CREATOR = new Creator<result>() {
        @Override
        public result createFromParcel(Parcel in) {
            return new result(in);
        }

        @Override
        public result[] newArray(int size) {
            return new result[size];
        }
    };

    public int getGoalsHomeTeam() {
        return goalsHomeTeam;
    }

    public void setGoalsHomeTeam(int goalsHomeTeam) {
        this.goalsHomeTeam = goalsHomeTeam;
    }

    public int getGoalsAwayTeam() {
        return goalsAwayTeam;
    }

    public void setGoalsAwayTeam(int goalsAwayTeam) {
        this.goalsAwayTeam = goalsAwayTeam;
    }
}
