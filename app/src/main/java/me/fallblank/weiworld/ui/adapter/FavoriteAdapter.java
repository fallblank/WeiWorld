package me.fallblank.weiworld.ui.adapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import me.fallblank.weiworld.R;
import me.fallblank.weiworld.bean.Favorite;
import me.fallblank.weiworld.bean.Weibo;
import me.fallblank.weiworld.ui.adapter.holder.weibo.BaseWeiboHolder;
import me.fallblank.weiworld.util.LogUtil;
import me.fallblank.weiworld.util.ReuseHolderMethod;

/**
 * Created by fallb on 2017/4/25.
 */

public class FavoriteAdapter extends BaseAdapter<Favorite.FavoritesBean, BaseWeiboHolder> {
    
    private FragmentManager mFManager;
    
    public FavoriteAdapter(Context context, List<Favorite.FavoritesBean> dataList) {
        super(context, dataList);
    }
    
    @Override
    public BaseWeiboHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_base_weibo_content, parent, false);
        itemView.setOnClickListener(this);
        BaseWeiboHolder holder = ReuseHolderMethod.getWeiboHolder(viewType, itemView);
        holder.setFManager(mFManager);
        return holder;
    }
    
    @Override
    public void onBindViewHolder(BaseWeiboHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        Favorite.FavoritesBean item = getItem(position);
        Weibo weibo = item.getStatus();
        if (weibo.getUser() == null) {
            LogUtil.d("id-fallblank :" + weibo.getIdstr());
        }
        holder.setContent(item.getStatus());
    }
    
    @Override
    public int getItemViewType(int position) {
        Favorite.FavoritesBean item = getItem(position);
        return ReuseHolderMethod.getWeiboType(item.getStatus());
    }
    
    @Override
    public int getItemCount() {
        return getSize();
    }
    
    public void setFManager(FragmentManager FManager) {
        mFManager = FManager;
    }
}
