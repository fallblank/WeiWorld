package me.fallblank.weiworld.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import me.fallblank.weiworld.bean.BaseBean;


/**
 * Created by fallb on 2017/4/5.
 */

public abstract class BaseAdapter<B extends BaseBean, T extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<T> implements View.OnClickListener{
    protected List<B> mDataList;
    protected Context mContext;
    protected OnRecyclerItemClickListener<B> mOnItemClickListener = null;

    public BaseAdapter(Context context, List<B> dataList) {
        mDataList = dataList;
        mContext = context;
    }
    
    @Override
    public void onBindViewHolder(T holder, int position) {
        B data = getItem(position);
        holder.itemView.setOnClickListener(this);
        holder.itemView.setTag(data);
        
    }
    
    public int getSize() {
        return mDataList.size();
    }

    public Context getContext() {
        return mContext;
    }

    public B getItem(int count) {
        if (count < 0 || count >= getSize()) {
            throw new ArrayIndexOutOfBoundsException("Adapter 居然越界了，了不得！");
        }
        return mDataList.get(count);
    }

    public void setOnItemClickListener(OnRecyclerItemClickListener<B> onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public interface OnRecyclerItemClickListener<B> {
        void onItemClicked(View item, B data);
    }
    
    @Override
    public void onClick(View v) {
        mOnItemClickListener.onItemClicked(v, (B) v.getTag());
    }
}
