package dev.teerayut.pml.matchs.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;
import dev.teerayut.pml.R;
import dev.teerayut.pml.matchs.item.ItemRound;
import dev.teerayut.pml.utils.AnimateButton;
import dev.teerayut.pml.utils.Config;

/**
 * Created by Teera-s.me on 30/8/2559.
 */
public class RoundsAdapter extends RecyclerView.Adapter<RoundsAdapter.ViewHolder> {

    private String tmp = null;
    private Context context;
    private List<ItemRound> itemRounds = new ArrayList<ItemRound>();

    public RoundsAdapter(FragmentActivity context, List<ItemRound> itemList) {
        this.context = context;
        this.itemRounds = itemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_match, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ItemRound item = itemRounds.get(position);
        if (hasDate(position)) {
            holder.matchTime.setVisibility(View.VISIBLE);
            holder.matchTime.setText(splitDate(item.getDate().replace("T", " ").replace("Z", "")));
        } else {
            holder.matchTime.setVisibility(View.GONE);
        }

        holder.namehome.setText(item.getHomeTeamName());
        holder.nameaway.setText(item.getAwayTeamName());
        holder.homeScore.setText(String.valueOf(item.getResult().getGoalsHomeTeam()));
        holder.awayScore.setText(String.valueOf(item.getResult().getGoalsAwayTeam()));

        if (getCurrentDate().equals(splitDate(item.getDate().replace("T", " ").replace("Z", "")))) {
            holder.matchTime.setBackgroundColor(context.getResources().getColor(android.R.color.holo_red_light));
        } else {
            holder.matchTime.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
        }

        if (item.getStatus().equals(Config.KEY_STATUS_FINISHED)) {
            holder.matchStatus.setText("FT");
            holder.matchStatus.setBackgroundDrawable(null);
        }

        if (item.getStatus().equals(Config.KEY_STATUS_IN_PLAY)) {
            holder.matchStatus.setText("-");
            holder.matchStatus.setTextColor(context.getResources().getColor(R.color.LimeGreen));
            holder.homeScore.setTextColor(context.getResources().getColor(R.color.LimeGreen));
            holder.awayScore.setTextColor(context.getResources().getColor(R.color.LimeGreen));
        }

        if (item.getStatus().equals(Config.KEY_STATUS_SCHEDULED) || item.getStatus().equals(Config.KEY_STATUS_TIMED)){
            holder.matchStatus.setText(splitTime(item.getDate().replace("T", " ").replace("Z", "")));
            holder.matchStatus.setBackgroundDrawable(null);
            holder.homeScore.setText("");
            holder.awayScore.setText("");
        }
    }

    private boolean hasDate(int position){
        if (position == 0)
            return true;
        return !splitDate(itemRounds.get(position).getDate().replace("T", " ").replace("Z", "")).equals(
                splitDate(itemRounds.get(position-1).getDate().replace("T", " ").replace("Z", "")));
    }

    private boolean hasTime(int position) {
        if (position == 0)
            return true;
        return !splitTime(itemRounds.get(position).getDate().replace("T", " ").replace("Z", "")).equals(
                splitTime(itemRounds.get(position-1).getDate().replace("T", " ").replace("Z", "")));
    }

    @Override
    public int getItemCount() {
        return itemRounds.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.matchTime) TextView matchTime;
        @Bind(R.id.nameHome) TextView namehome;
        @Bind(R.id.nameAway) TextView nameaway;
        @Bind(R.id.scoreHome) TextView homeScore;
        @Bind(R.id.scoreAway) TextView awayScore;
        @Bind(R.id.status) TextView matchStatus;
        @Bind(R.id.match_item) LinearLayout rowItem;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private String subDate(String str){
        int last = str.lastIndexOf("T");
        String dateTime = str.substring(0, last);
        return dateTime;
    }

    private String splitDate(String str) {
        String[] d = str.split(" ");
        return d[0];
    }

    private String splitTime(String str) {
        String[] d = str.split(" ");
        return splitHHMMss(d[1]);
    }

    private String splitHHMMss(String str) {
        String[] d = str.split(":");
        String h = null;
        int hour = Integer.parseInt(d[0]) + 7;
        if (hour > 24) {
            hour = hour - 24;
            if (hour < 10) {
                h = "0" + hour;
            } else {
                h = hour + "";
            }
        } else {
            h = hour + "";
        }
        String timeLocal = h + ":" + d[1];
        return timeLocal;
    }

    private String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDateandTime = sdf.format(new Date());
        return currentDateandTime;
    }

    private String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String currentTime = sdf.format(new Date());
        return currentTime;
    }
}
