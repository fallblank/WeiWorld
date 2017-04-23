package me.fallblank.weiworld.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by fallb on 2017/4/14.
 */

public class AppUtil {

    //存放软件相关元数据的sharepreference文件名
    public static final String APP_METADATA = "AppMetadata";

    public static final String KEY_VERSION = "version";

    public static final String KEY_FIRST_LAUNCH = "firstLaunch";

    public static int getVersion(Context context) {
        try {
            PackageInfo info = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), PackageManager.GET_CONFIGURATIONS);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            LogUtil.d("Error in get version", e);
        }
        return 0;
    }

}
