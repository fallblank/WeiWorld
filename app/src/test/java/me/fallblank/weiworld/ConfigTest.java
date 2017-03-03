package me.fallblank.weiworld;

import org.junit.Test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by fallb on 2017/3/2.
 * Testing whether can get private information or not
 */

public class ConfigTest {
    @Test
    public void canGetKey(){
        assertNotNull(BuildConfig.APP_KEY);
        assertNotEquals(BuildConfig.APP_KEY,"");
    }

    @Test
    public void canGetSecret(){
        assertNotNull(BuildConfig.APP_SECRET);
        assertNotEquals(BuildConfig.APP_SECRET,"");
    }

    @Test
    public void canGetScope(){
        assertNotNull(BuildConfig.APP_SCOPE);
        assertNotEquals(BuildConfig.APP_SCOPE,"");
    }


}
