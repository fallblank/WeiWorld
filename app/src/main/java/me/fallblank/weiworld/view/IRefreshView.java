package me.fallblank.weiworld.view;

/**
 * Created by fallb on 2017/4/5.
 */

public interface IRefreshView extends IView {

    //刷新开始时调用
    public void refreshStart();

    //刷新成功结束时调用
    public void refreshSuccess(int size);

    //刷新失败结束时调用
    public void refreshFail(Throwable throwable);
}
