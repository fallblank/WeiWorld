package me.fallblank.weiworld;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import me.fallblank.weiworld.util.TimeFormatter;

/**
 * Created by fallb on 2017/4/6.
 */

public class TimeFormatterTest {
    SimpleDateFormat mDateFormat;

    @Before
    public void setup(){
        mDateFormat = new SimpleDateFormat(TimeFormatter.TIME_FORMAT_PARTTERN, Locale.ENGLISH);
    }

    @Test
    public void testFormat(){
        Date date = new Date();
        String str = mDateFormat.format(date);
        System.out.println(str);
    }

    @Test
    public void testParse(){
        String str = "Thu Mar 30 15:19:53 +0800 2017";
        try {
            Date date = mDateFormat.parse(str);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            System.out.println("Year: "+calendar.get(Calendar.YEAR));
            System.out.println("Month: "+calendar.get(Calendar.MONTH));
            System.out.println("Day: "+calendar.get(Calendar.DAY_OF_MONTH));
        } catch (ParseException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }
}
