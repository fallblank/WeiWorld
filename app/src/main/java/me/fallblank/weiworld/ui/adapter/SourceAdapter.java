package me.fallblank.weiworld.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import me.fallblank.weiworld.R;
import me.fallblank.weiworld.bean.SourceBean;
import me.fallblank.weiworld.ui.adapter.holder.SourceHolder;

/**
 * Created by fallb on 2017/5/8.
 */

public class SourceAdapter extends BaseAdapter<SourceBean, SourceHolder> {
    public SourceAdapter(Context context, List<SourceBean> dataList) {
        super(context, dataList);
    }
    
    @Override
    public SourceHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_source, parent, false);
        SourceHolder holder = new SourceHolder(view);
        return holder;
    }
    
    @Override
    public void onBindViewHolder(SourceHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.setContetn(getItem(position));
    }
    
    @Override
    public int getItemCount() {
        return getSize();
    }
}
