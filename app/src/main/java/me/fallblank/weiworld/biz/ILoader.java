package me.fallblank.weiworld.biz;

/**
 * Created by fallb on 2017/4/5.
 */

public interface ILoader {

    public void start();

    public void complete(int size);

    public void error(Throwable throwable);


}
