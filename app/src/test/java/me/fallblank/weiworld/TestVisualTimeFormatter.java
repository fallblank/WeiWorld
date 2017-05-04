package me.fallblank.weiworld;

import org.junit.Test;

import java.util.Date;

import me.fallblank.weiworld.util.TimeFormatter;

/**
 * Created by fallb on 2017/5/4.
 */

public class TestVisualTimeFormatter {
    @Test
    public void test() {
        System.out.println(TimeFormatter.getVisualTimestamp(new Date()));
    }
}
