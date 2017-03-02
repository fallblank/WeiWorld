package me.fallblank.weiworld;

import org.junit.Test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by fallb on 2017/3/2.
 */

public class ConfigTest {
    @Test
    public void canGetKey(){
        assertNotNull(BuildConfig.APP_KEY);
        assertNotEquals(BuildConfig.APP_KEY,"");
    }
}
