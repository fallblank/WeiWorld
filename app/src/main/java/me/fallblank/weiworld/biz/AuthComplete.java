package me.fallblank.weiworld.biz;

/**
 * Created by fallb on 2017/3/27.
 */

public interface AuthComplete {
    /**
     * it means the authority completed
     */
    public void success();

    public void fail();

    public void cancer();

    public void exception(Throwable throwable);
}
