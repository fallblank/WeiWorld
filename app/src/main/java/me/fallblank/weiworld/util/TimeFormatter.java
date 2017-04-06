package me.fallblank.weiworld.util;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by fallb on 2017/4/6.
 */

public class TimeFormatter {
    /**
     * 格式：Thu Mar 30 15:19:53 +0800 2017
     * 模式：星期 月份 日期 时:分:秒 时区 年份
     *
     */
    public static final String TIME_FORMAT_PARTTERN = "EEE MMM dd HH:mm:ss Z yyyy";

    public static String formatTime(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        String year = calendar.get(Calendar.YEAR)+"";
        String month = (calendar.get(Calendar.MONTH)+1)+"";
        String day = calendar.get(Calendar.DAY_OF_MONTH)+"";
        String hour = calendar.get(Calendar.HOUR_OF_DAY)+"";
        String minute = calendar.get(Calendar.MINUTE)+"";
        return year+"年"+month+"月"+day+"日 "+hour+":"+minute;

    }
}
