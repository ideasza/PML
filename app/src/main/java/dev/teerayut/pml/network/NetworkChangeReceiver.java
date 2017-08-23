package dev.teerayut.pml.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import dev.teerayut.pml.matchs.RoundsFragment;
import dev.teerayut.pml.table.TableStandingFragment;


public class NetworkChangeReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(final Context context, final Intent intent) {

		try {
			String status = NetworkUtil.getConnectivityStatusString(context);
			if (status.equals("Wifi enabled") || status.equals("Mobile data enabled")) {
				TableStandingFragment.getInstance().detectWifiConnected("connect");
				RoundsFragment.getInstance().detectWifiConnected("connect");
			} else {
				TableStandingFragment.getInstance().detectWifiConnected("not connect");
				RoundsFragment.getInstance().detectWifiConnected("not connect");
			}
		} catch (Exception e) {

		}
	}
}
