package me.fallblank.weiworld.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;

import me.fallblank.weiworld.App;

/**
 * Created by fallb on 2017/4/11.
 */

public class NetworkChecker {

    public void check(Context context) {
        App app = (App) context.getApplicationContext();
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = null;
        NetworkInfo mobile = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            wifi = getNetworkInfo_above23(cm, 0);
            mobile = getNetworkInfo_above23(cm, 1);
        } else {
            wifi = getNetworkInfo_below23(cm, 0);
            mobile = getNetworkInfo_below23(cm, 1);
        }
        if (wifi != null) {
            app.setWifiStatue(wifi.isConnected());
        }
        if (mobile != null) {
            app.setMobileStatue(mobile.isConnected());
        }
    }

    private NetworkInfo getNetworkInfo_below23(ConnectivityManager cm, int type) {
        NetworkInfo networkInfo = null;
        if (type == 0) {
            networkInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        } else if (type == 1) {
            networkInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        }
        return networkInfo;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private NetworkInfo getNetworkInfo_above23(ConnectivityManager cm, int type) {
        NetworkInfo networkInfo = null;
        Network[] allNetWork = cm.getAllNetworks();
        for (Network network : allNetWork) {
            networkInfo = cm.getNetworkInfo(network);
            if (networkInfo.getTypeName().equals("WIFI") && type == 0) {
                return networkInfo;
            }
            if (networkInfo.getTypeName().equals("MOBILE") && type == 1) {
                return networkInfo;
            }
        }
        return networkInfo;
    }
}
