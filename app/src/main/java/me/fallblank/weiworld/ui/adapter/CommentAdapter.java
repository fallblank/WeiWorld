package me.fallblank.weiworld.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import me.fallblank.weiworld.R;
import me.fallblank.weiworld.bean.Comment;
import me.fallblank.weiworld.ui.adapter.holder.CommentHolder;

/**
 * Created by fallb on 2017/5/4.
 */

public class CommentAdapter extends BaseAdapter<Comment, CommentHolder> {
    
    public CommentAdapter(Context context, List<Comment> dataList) {
        super(context, dataList);
    }
    
    @Override
    public CommentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_comment, parent, false);
        CommentHolder holder = new CommentHolder(view);
        return holder;
    }
    
    @Override
    public void onBindViewHolder(CommentHolder holder, int position) {
        Comment comment = getItem(position);
        holder.setContent(comment);
    }
    
    @Override
    public int getItemCount() {
        return getSize();
    }
}
