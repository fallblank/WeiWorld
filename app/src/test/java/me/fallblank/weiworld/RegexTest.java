package me.fallblank.weiworld;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by fallb on 2017/4/12.
 */

public class RegexTest {

    @Test
    public void testAtRegex(){
        String atRegex  = "(@)(\\w+)(\\s)";
        String test = "@song :2333@zheng 5611";
        Pattern pattern = Pattern.compile(atRegex);
        Matcher matcher = pattern.matcher(test);
        while (matcher.find()){
            System.out.println("group: "+matcher.group(2));
            System.out.println("start: "+matcher.start(1));
            System.out.println("end: "+matcher.end(2));
        }

    }
}
