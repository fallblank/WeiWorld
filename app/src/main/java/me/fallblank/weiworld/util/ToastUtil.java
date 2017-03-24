package me.fallblank.weiworld.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by fallb on 2017/3/9.
 */

public final class ToastUtil {
    private static Toast sToast;

    //most common style
    public static void show(Context context, String content) {
        if (null != sToast) {
            //disappear the showing toast
            sToast.cancel();
        }
        sToast = Toast.makeText(context, content, Toast.LENGTH_LONG);
        sToast.show();
    }
}
