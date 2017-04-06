package me.fallblank.weiworld.model;


import android.content.Context;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import me.fallblank.weiworld.App;
import me.fallblank.weiworld.bean.ContentResponse;
import me.fallblank.weiworld.bean.Weibo;
import me.fallblank.weiworld.bean.WeiboComparator;
import me.fallblank.weiworld.biz.ILoader;
import me.fallblank.weiworld.biz.IWeiboContent;
import me.fallblank.weiworld.impl.WeiboContentImp;

/**
 * Created by fallb on 2017/3/31.
 */

public class ContentManager extends BaseModel {
    private static final String kEY_ACCESS_TOKEN = "access_token";
    private static final String kEY_COUNT = "count";
    private static final String kEY_PAGE = "page";


    private int mPageSize = 15;
    private int mPageIndex = 1;
    private IWeiboContent mServer;
    private List<Weibo> mDataList;
    private HashMap<String,String> mQueryMap;
    private ILoader mLoadListner;

    public ContentManager(Context context,List<Weibo> dataList,ILoader loadListner){
        this.mDataList = dataList;
        WeiboContentImp imp = new WeiboContentImp();
        this.mServer = imp.getWeiboContent();
        initQueryMap(context);
        this.mLoadListner = loadListner;
    }

    public void initQueryMap(Context context){
        mQueryMap = new HashMap<>();
        App app = (App) context;
        String token = app.getAccessToken().getToken();
        mQueryMap.put(ContentManager.kEY_ACCESS_TOKEN,token);
    }

    /**
     * 获取最新数据
     * @return 新增数据数量
     */
    public void reflesh(){
        mQueryMap.put(ContentManager.kEY_COUNT,mPageSize+"");
        mQueryMap.put(ContentManager.kEY_PAGE,mPageIndex+"");
        mServer.listLastWeibo(mQueryMap)
                .subscribeOn(Schedulers.io())
                .map(new Function<ContentResponse, ContentResponse>() {
                    @Override
                    public ContentResponse apply(@NonNull ContentResponse contentResponse) throws Exception {
                        WeiboComparator comparator = new WeiboComparator();
                        Collections.sort(contentResponse.getStatuses(),comparator);
                        return contentResponse;
                    }
                })
                .flatMap(new Function<ContentResponse, ObservableSource<Weibo>>() {
                    @Override
                    public ObservableSource<Weibo> apply(@NonNull ContentResponse contentResponse) throws Exception {
                        List<Weibo> weiboList = contentResponse.getStatuses();
                        return Observable.fromIterable(weiboList);
                    }
                })
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Weibo>() {
                    int size = 0;
                    @Override
                    public void onSubscribe(Disposable d) {
                        mLoadListner.start();
                    }

                    @Override
                    public void onNext(Weibo weibo) {
                        mDataList.add(weibo);
                        size ++;
                    }

                    @Override
                    public void onError(Throwable e) {
                        mLoadListner.error(e);
                    }

                    @Override
                    public void onComplete() {
                        mLoadListner.complete(size);
                    }
                });
    }

    @Override
    protected void store() {

    }

    @Override
    protected void restore() {

    }
}
