package dev.teerayut.pml.table.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.os.StrictMode;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.StreamEncoder;
import com.bumptech.glide.load.resource.file.FileToStreamDecoder;
import com.caverock.androidsvg.SVG;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import dev.teerayut.pml.R;
import dev.teerayut.pml.table.item.StandingItem;
import dev.teerayut.pml.utils.Config;
import dev.teerayut.pml.utils.MyApplication;
import dev.teerayut.pml.utils.svg.SvgDecoder;
import dev.teerayut.pml.utils.svg.SvgDrawableTranscoder;
import dev.teerayut.pml.utils.svg.SvgSoftwareLayerSetter;

/**
 * Created by teera-s on 8/25/2016 AD.
 */
public class TableStandingsAdapter extends RecyclerView.Adapter<TableStandingsAdapter.ViewHolder> {

    private Context context;
    private GenericRequestBuilder<Uri, InputStream, SVG, PictureDrawable> requestBuilder;
    private List<StandingItem> standingItemList = new ArrayList<StandingItem>();
    public TableStandingsAdapter(FragmentActivity activity, List<StandingItem> standingItemList) {
        this.context = activity;
        this.standingItemList = standingItemList;
    }

    public TableStandingsAdapter(FragmentActivity activity) {
        this.context = activity;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<StandingItem> getStandingItemList() {
        return standingItemList;
    }

    public void setStandingItemList(List<StandingItem> standingItemList) {
        this.standingItemList = standingItemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        StandingItem item = standingItemList.get(position);
        holder.position.setText("" + item.getPosition());
        holder.textViewName.setText(item.getTeamName());
        holder.textViewPlayed.setText("" + item.getPlayedGames());
        holder.textViewWins.setText("" + item.getWins());
        holder.textViewDraws.setText("" + item.getDraws());
        holder.textViewLoses.setText("" + item.getLosses());
        holder.textViewGoals.setText("" + item.getGoalDifference());
        holder.textViewPoints.setText("" + item.getPoints());

        if (item.getGoalDifference() < 0) {
            holder.textViewGoals.setTextColor(Color.RED);
        } else {
            holder.textViewGoals.setTextColor(Color.BLACK);
        }

        String extension = item.getCrestURI().substring(item.getCrestURI().lastIndexOf("."));
        if (extension.equals(".svg")) {
            GenericRequestBuilder<Uri,InputStream,SVG,PictureDrawable>
                    requestBuilder = Glide.with(context)
                    .using(Glide.buildStreamModelLoader(Uri.class, context), InputStream.class)
                    .from(Uri.class)
                    .as(SVG.class)
                    .transcode(new SvgDrawableTranscoder(), PictureDrawable.class)
                    .sourceEncoder(new StreamEncoder())
                    .cacheDecoder(new FileToStreamDecoder<SVG>(new SvgDecoder()))
                    .decoder(new SvgDecoder())
                    .listener(new SvgSoftwareLayerSetter<Uri>());

            Uri uri = Uri.parse(item.getCrestURI());
            requestBuilder
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .load(uri)
                    .into(holder.imageViewLogo);
        } else {
            Glide.with(context)
                    .load(item.getCrestURI())
                    .into(holder.imageViewLogo);
        }

        if (position > 16) {
            holder.position.setBackgroundColor(Color.parseColor("#ff8080"));
        } else if (position < 3) {
            holder.position.setBackgroundColor(Color.parseColor("#554dff"));
        } else if (position == 3) {
            holder.position.setBackgroundColor(Color.parseColor("#4ecbff"));
        } else if (position == 4) {
            holder.position.setBackgroundColor(Color.parseColor("#ffcf4d"));
        } else {
            holder.position.setBackgroundColor(Color.parseColor("#ffffff"));
        }
    }

    @Override
    public int getItemCount() {
        return standingItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.layoutCardView) LinearLayout linearLayout;
        @Bind(R.id.teamImg) ImageView imageViewLogo;
        @Bind(R.id.textNumber) TextView position;
        @Bind(R.id.teamName) TextView textViewName;
        @Bind(R.id.textPlayed) TextView textViewPlayed;
        @Bind(R.id.textWon) TextView textViewWins;
        @Bind(R.id.textDraw) TextView textViewDraws;
        @Bind(R.id.textLose) TextView textViewLoses;
        @Bind(R.id.textGD) TextView textViewGoals;
        @Bind(R.id.textPoints) TextView textViewPoints;
        @Bind(R.id.teamStatus) ImageView teamStatus;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private String teamCurrentPosition(String name, int position) {
        String p = "" + position;
        try {
            if (!MyApplication.getInstance().getPrefManager().getPreferrence(Config.KEY_TIMESTAMP).equals(getCurrentDate())) {
                MyApplication.getInstance().getPrefManager().setPreferrence(Config.KEY_TIMESTAMP, getCurrentDate());
                MyApplication.getInstance().getPrefManager().setPreferrence(Config.KEY_TEAMPOSITION, name + "|" + p);
            } else {
                String posion = MyApplication.getInstance().getPrefManager().getPreferrence(Config.KEY_TEAMPOSITION);
                String[] pos = posion.split("|");
                return pos[1];
            }
        } catch (Exception ex) {
            MyApplication.getInstance().getPrefManager().setPreferrence(Config.KEY_TIMESTAMP, getCurrentDate());
            MyApplication.getInstance().getPrefManager().setPreferrence(Config.KEY_TEAMPOSITION, name + "|" + p);
        }
        return "99";
    }

    private String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDateandTime = sdf.format(new Date());
        return currentDateandTime;
    }
}
