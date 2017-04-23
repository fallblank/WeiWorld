package me.fallblank.weiworld.util;

import android.content.Context;

import com.sina.weibo.sdk.net.NetUtils;

import me.fallblank.weiworld.R;

import static com.sina.weibo.sdk.openapi.legacy.CommonAPI.CAPITAL.g;
import static com.sina.weibo.sdk.openapi.legacy.CommonAPI.CAPITAL.m;

/**
 * Created by fallb on 2017/4/19.
 */

public class ColorGenerator {

    private int[] sColorTable;
    private int mLength;
    private static ColorGenerator mInstance;


    private ColorGenerator(Context context) {
        sColorTable = context.getResources().getIntArray(R.array.colorCard);
        mLength = sColorTable.length;
    }

    public int generate() {
        int index = (int) (Math.random() * mLength);
        return sColorTable[index];
    }

    public static int genetateColor(Context context) {
        if (null == mInstance) {
            synchronized (ColorGenerator.class) {
                if (null == mInstance) {
                    mInstance = new ColorGenerator(context);
                }
            }
        }
        return mInstance.generate();
    }
}
