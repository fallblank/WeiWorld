package me.fallblank.weiworld.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import static com.sina.weibo.sdk.openapi.legacy.CommonAPI.CAPITAL.s;

/**
 * Created by fallb on 2017/4/10.
 */

public class DeviceUtil {

    private DisplayMetrics mDisplayMetrics;

    public DeviceUtil(Context context){
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        mDisplayMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(mDisplayMetrics);
    }

    public int getScreenWidth(){
        return mDisplayMetrics.widthPixels;
    }

    public int getScreenHeight(){
        return mDisplayMetrics.heightPixels;
    }

    public DisplayMetrics getDisplayMatrics(){
        return mDisplayMetrics;
    }

}
