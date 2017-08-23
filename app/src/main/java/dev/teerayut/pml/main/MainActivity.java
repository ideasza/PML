package dev.teerayut.pml.main;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.location.Location;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.analytics.FirebaseAnalytics;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import dev.teerayut.pml.R;
import dev.teerayut.pml.base.BaseMvpActivity;
import dev.teerayut.pml.fragmentadpater.FragementAdapter;
import dev.teerayut.pml.utils.Config;
import dev.teerayut.pml.utils.ConnectionDetector;
import dev.teerayut.pml.utils.MyApplication;


public class MainActivity extends BaseMvpActivity<MainInterface.presenter> implements MainInterface.view {
    private static final String TAG = MainActivity.class.getSimpleName();

   private FragementAdapter adapter;
    private FirebaseAnalytics mFirebaseAnalytics;

    private void setApplicationFirstOpen(String str){
        MyApplication.getInstance().getPrefManager().setPreferrence(Config.FIRST_OPEN, str);
    }

    private String getApplicationFirstOpen(){
        return MyApplication.getInstance().getPrefManager().getPreferrence(Config.FIRST_OPEN);
    }

    @Override
    protected void onStart() {
        super.onStart();
        boolean isNetworkAvailable = ConnectionDetector.isConnectingToInternet(this);
        if (!isNetworkAvailable) {
            dialogNetWork();
        }
    }

    @Override
    public void onPause() {
        if (adView != null) {
            adView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (adView != null) {
            adView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }

    @Override
    public MainInterface.presenter createPresenter() {
        return MainPresenter.create();
    }

    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.pager) ViewPager mViewPager;
    @Bind(R.id.tab_layout) TabLayout tabLayout;
    @Bind(R.id.adView) AdView adView;
    @Override
    public int getLayoutView() {
        return R.layout.activity_main;
    }

    @Override
    public void bindView() {
        ButterKnife.bind(this);
    }

    @Override
    public void setupInstance() {
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(MainActivity.this);
        adapter = new FragementAdapter(getSupportFragmentManager());
        MobileAds.initialize(this, "ca-app-pub-3143593622251790~6865314303");
    }

    @Override
    public void setupView() {
        mFirebaseAnalytics.setAnalyticsCollectionEnabled(true);
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(0);
        setTabLayout();
    }

    private void setTabLayout() {
        tabLayout.addTab(tabLayout.newTab().setText(R.string.title_table));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.title_fixtures));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setFillViewport(true);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition(), true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void initialize() {
        try {
            if (getApplicationFirstOpen().equals(null)) {
                ViewDialog alert = new ViewDialog();
                alert.showDialog(MainActivity.this);
                setApplicationFirstOpen("opened");
            }
        } catch (Exception e) {
            Log.e(TAG, "First open app: " + e.toString());
            ViewDialog alert = new ViewDialog();
            alert.showDialog(MainActivity.this);
            setApplicationFirstOpen("opened");
        }

        Location location = new Location("AdMobProvider");
        location.setLatitude(13.543296);
        location.setLatitude(100.924562);
        adView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .setLocation(location)
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        adView.loadAd(adRequest);
    }

    public void setToolbar(String title) {
        toolbar.setTitle(title);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimaryDark));
        setSupportActionBar(toolbar);
    }

    private void dialogNetWork() {
        new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText(this.getResources().getString(R.string.dialog_title_warning))
                .setContentText(this.getResources().getString(R.string.dialog_text_msg))
                .setCancelText(this.getResources().getString(R.string.dialog_button_cancel))
                .setConfirmText(this.getResources().getString(R.string.dialog_button_confirm))
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismiss();
                        finish();
                    }
                })
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismiss();
                        Intent intent = new Intent(android.provider.Settings.ACTION_SETTINGS);
                        startActivityForResult(intent, Config.REQUEST_SETTINGS);
                    }
                })
                .show();
    }

    public class ViewDialog {

        public void showDialog(Activity activity){
            final Dialog dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.dialog);

            Button dialogButton = (Button) dialog.findViewById(R.id.btn_dialog);
            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        }
    }
}
