package dev.teerayut.pml.utils;

import android.app.Application;
import android.support.multidex.MultiDexApplication;

/**
 * Created by teera-s on 5/19/2016 AD.
 */
public class MyApplication extends /*Application,*/ MultiDexApplication {

    public static final String TAG = MyApplication.class.getSimpleName();

    private static MyApplication mInstance;

    private MyPreferenceManager pref;


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

    public MyPreferenceManager getPrefManager() {
        if (pref == null) {
            pref = new MyPreferenceManager(this);
        }
        return pref;
    }
}
