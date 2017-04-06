package me.fallblank.weiworld.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import me.fallblank.weiworld.bean.BaseBean;


/**
 * Created by fallb on 2017/4/5.
 */

public abstract class BaseAdapter<B extends BaseBean,T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<T>{
    protected List<B> mDataList;
    protected Context mContext;

    public BaseAdapter(Context context,List<B> dataList){
        mDataList = dataList;
        mContext = context;
    }

    public int getSize(){
        return mDataList.size();
    }

    public Context getContext(){
        return mContext;
    }

    public B getItem(int count){
        if (count < 0 || count >= getSize()){
            throw new ArrayIndexOutOfBoundsException("Adapter 居然越界了，了不得！");
        }
        return mDataList.get(count);
    }

}
