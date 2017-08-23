package dev.teerayut.pml.table.item;


import android.os.Parcel;
import android.os.Parcelable;

import dev.teerayut.pml.base.adapter.BaseItem;


/**
 * Created by teerayut.k on 7/24/2017.
 */

public class StandingItem extends BaseItem implements Parcelable {

    private int position;
    private String teamName;
    private String crestURI;
    private int playedGames;
    private int points;
    private int goals;
    private int goalsAgainst;
    private int goalDifference;
    private int wins;
    private int draws;
    private int losses;

    public StandingItem() {

    }

    public int getPosition() {
        return position;
    }

    public StandingItem setPosition(int position) {
        this.position = position;
        return this;
    }

    public String getTeamName() {
        return teamName;
    }

    public StandingItem setTeamName(String teamName) {
        this.teamName = teamName;
        return this;
    }

    public String getCrestURI() {
        return crestURI;
    }

    public StandingItem setCrestURI(String crestURI) {
        this.crestURI = crestURI;
        return this;
    }

    public int getPlayedGames() {
        return playedGames;
    }

    public StandingItem setPlayedGames(int playedGames) {
        this.playedGames = playedGames;
        return this;
    }

    public int getPoints() {
        return points;
    }

    public StandingItem setPoints(int points) {
        this.points = points;
        return this;
    }

    public int getGoals() {
        return goals;
    }

    public StandingItem setGoals(int goals) {
        this.goals = goals;
        return this;
    }

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    public StandingItem setGoalsAgainst(int goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
        return this;
    }

    public int getGoalDifference() {
        return goalDifference;
    }

    public StandingItem setGoalDifference(int goalDifference) {
        this.goalDifference = goalDifference;
        return this;
    }

    public int getWins() {
        return wins;
    }

    public StandingItem setWins(int wins) {
        this.wins = wins;
        return this;
    }

    public int getDraws() {
        return draws;
    }

    public StandingItem setDraws(int draws) {
        this.draws = draws;
        return this;
    }

    public int getLosses() {
        return losses;
    }

    public StandingItem setLosses(int losses) {
        this.losses = losses;
        return this;
    }

    @Override
    public boolean equals( Object o ){
        if( this == o ) return true;
        if( o == null || getClass() != o.getClass() ) return false;

        StandingItem that = (StandingItem) o;

        if( position != that.position ) return false;
        if( teamName != null ? !teamName.equals( that.teamName ) : that.teamName != null ) return false;
        if( crestURI != null ? !crestURI.equals( that.crestURI ) : that.crestURI != null ) return false;
        if( playedGames != that.playedGames ) return false;
        if( points != that.points ) return false;
        if( goals != that.goals ) return false;
        if( goalsAgainst != that.goalsAgainst ) return false;
        if( goalDifference != that.goalDifference ) return false;
        if( wins != that.wins ) return false;
        if( draws != that.draws ) return false;
        return losses != that.losses ? false : true;
    }

    @Override
    public int hashCode(){
        int result = teamName != null ? teamName.hashCode() : 0;
        result = 31 * result + ( crestURI != null ? crestURI.hashCode() : 0 );
        result = 31 * result + position;
        result = 31 * result + playedGames;
        result = 31 * result + points;
        result = 31 * result + goals;
        result = 31 * result + goalsAgainst;
        result = 31 * result + goalDifference;
        result = 31 * result + wins;
        result = 31 * result + draws;
        result = 31 * result + losses;
        return result;
    }

    @Override
    public BaseItem clone() throws CloneNotSupportedException{
        StandingItem item = new StandingItem()
                .setPosition(position)
                .setTeamName(teamName)
                .setCrestURI(crestURI)
                .setPlayedGames(playedGames)
                .setPoints(points)
                .setGoals(goals)
                .setGoalsAgainst(goalsAgainst)
                .setGoalDifference(goalDifference)
                .setWins(wins)
                .setDraws(draws)
                .setLosses(losses);
        return item;
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags ){
        super.writeToParcel( dest, flags );
        dest.writeInt( this.position );
        dest.writeString( this.teamName );
        dest.writeString( this.crestURI );
        dest.writeInt( this.playedGames );
        dest.writeInt( this.points );
        dest.writeInt( this.goals );
        dest.writeInt( this.goalsAgainst );
        dest.writeInt( this.goalDifference );
        dest.writeInt( this.wins );
        dest.writeInt( this.draws );
        dest.writeInt( this.losses );
    }

    protected StandingItem( Parcel in ){
        super( in );
        this.position = in.readInt();
        this.teamName = in.readString();
        this.crestURI = in.readString();
        this.playedGames = in.readInt();
        this.points = in.readInt();
        this.goals = in.readInt();
        this.goalsAgainst = in.readInt();
        this.goalDifference = in.readInt();
        this.wins = in.readInt();
        this.draws = in.readInt();
        this.losses = in.readInt();
    }

    public static final Creator<StandingItem> CREATOR = new Creator<StandingItem>(){
        @Override
        public StandingItem createFromParcel( Parcel source ){
            return new StandingItem( source );
        }

        @Override
        public StandingItem[] newArray( int size ){
            return new StandingItem[size];
        }
    };

}
