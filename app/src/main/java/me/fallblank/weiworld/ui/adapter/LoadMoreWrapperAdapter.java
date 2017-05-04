package me.fallblank.weiworld.ui.adapter;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by fallb on 2017/4/28.
 * 实现recycler view中底部刷新视图实现
 */

public class LoadMoreWrapperAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public interface OnBottomListener {
        void onBottom();
    }
    
    private final static int BOTTOM_VIEW_TYPE = 0xffffff;
    
    private boolean mHideBottomView = false;
    
    private OnBottomListener mBottomListener = null;
    
    private int mItemCount = 0;
    
    private RecyclerView.Adapter mRealAdapter;
    
    @LayoutRes
    private int mBottomLayout;
    
    public LoadMoreWrapperAdapter(RecyclerView.Adapter adapter, @LayoutRes int bottomLayout) {
        this.mRealAdapter = adapter;
        mBottomLayout = bottomLayout;
    }
    
    @Override
    public int getItemViewType(int position) {
        if (mHideBottomView || position < mItemCount - 1) {
            return mRealAdapter.getItemViewType(position);
        }
        return BOTTOM_VIEW_TYPE;
    }
    
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType != BOTTOM_VIEW_TYPE) {
            return mRealAdapter.onCreateViewHolder(parent, viewType);
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(mBottomLayout, parent, false);
        return new BottomViewHolder(view);
    }
    
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mHideBottomView || position < mItemCount - 1) {
            mRealAdapter.onBindViewHolder(holder, position);
            return;
        }
        if (mBottomListener != null) {
            mBottomListener.onBottom();
        }
    }
    
    @Override
    public int getItemCount() {
        int realCount = mRealAdapter.getItemCount();
        if (mHideBottomView) {
            mItemCount = realCount;
            return realCount;
        }
        if (realCount == 0) {
            mItemCount = 0;//空视图不显示加载更多视图
        } else {
            mItemCount = realCount + 1;
        }
        return mItemCount;
    }
    
    //自定义数据变化通知,调用顺序很重要
    public void selfNotifyDataSetChanged() {
        mRealAdapter.notifyDataSetChanged();
        this.notifyDataSetChanged();
    }
    
    public void setHideBottomView(boolean hide) {
        mHideBottomView = hide;
        selfNotifyDataSetChanged();
    }
    
    public void setBottomListener(OnBottomListener bottomListener) {
        mBottomListener = bottomListener;
    }
    
    private class BottomViewHolder extends RecyclerView.ViewHolder {
        
        public BottomViewHolder(View itemView) {
            super(itemView);
        }
    }
}
