package me.fallblank.weiworld.ui.adapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import me.fallblank.weiworld.R;
import me.fallblank.weiworld.bean.Weibo;
import me.fallblank.weiworld.ui.adapter.holder.weibo.BaseWeiboHolder;
import me.fallblank.weiworld.util.ReuseHolderMethod;

/**
 * Created by fallb on 2017/4/5.
 */

public class ContentAdapter extends BaseAdapter<Weibo, BaseWeiboHolder> {
    
    private FragmentManager mFManager;
    
    public ContentAdapter(Context context, FragmentManager fm, List<Weibo> dataList) {
        super(context, dataList);
        this.mFManager = fm;
    }
    
    @Override
    public BaseWeiboHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_base_weibo_content, parent, false);
        BaseWeiboHolder holder = ReuseHolderMethod.getWeiboHolder(viewType, itemView);
        holder.setFManager(mFManager);
        return holder;
    }
    
    @Override
    public void onBindViewHolder(BaseWeiboHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        Weibo weibo = getItem(position);
        holder.setContent(weibo);
    }
    
    @Override
    public int getItemCount() {
        return getSize();
    }
    
    @Override
    public int getItemViewType(int position) {
        Weibo weibo = getItem(position);
        return ReuseHolderMethod.getWeiboType(weibo);
    }
}
