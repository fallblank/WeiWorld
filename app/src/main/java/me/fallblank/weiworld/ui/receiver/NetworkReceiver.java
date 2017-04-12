package me.fallblank.weiworld.ui.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import me.fallblank.weiworld.util.Constant;
import me.fallblank.weiworld.util.NetworkChecker;
import me.fallblank.weiworld.util.ToastUtil;

/**
 * Created by fallb on 2017/4/11.
 */

public class NetworkReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Constant.ACTION_CONNECTIVITY_CHANGE)) {
            ToastUtil.show(context, "网络发生变化");
            NetworkChecker checker = new NetworkChecker();
            checker.check(context);
        }
    }


}
