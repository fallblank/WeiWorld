package me.fallblank.weiworld.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import me.fallblank.weiworld.R;
import me.fallblank.weiworld.bean.Weibo;
import me.fallblank.weiworld.ui.adapter.holder.BaseWeiboHolder;
import me.fallblank.weiworld.util.ReuseHolderMethod;

/**
 * Created by fallb on 2017/4/5.
 */

public class ContentAdapter extends BaseAdapter<Weibo, BaseWeiboHolder> implements View.OnClickListener {

    public ContentAdapter(Context context, List<Weibo> dataList) {
        super(context, dataList);
    }

    @Override
    public BaseWeiboHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_base_weibo_content, parent, false);
        itemView.setOnClickListener(this);
        BaseWeiboHolder holder = new BaseWeiboHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(BaseWeiboHolder holder, int position) {
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

    @Override
    public void onClick(View v) {
        mOnItemClickListener.onItemClicked(v, (Weibo) v.getTag());
    }
}
