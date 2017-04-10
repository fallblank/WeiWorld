package me.fallblank.weiworld.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import me.fallblank.weiworld.R;
import me.fallblank.weiworld.bean.Weibo;
import me.fallblank.weiworld.util.TimeFormatter;

/**
 * Created by fallb on 2017/4/5.
 */

public class ContentAdapter extends BaseAdapter<Weibo, BaseWeiboHolder> {

    public ContentAdapter(Context context, List<Weibo> dataList) {
        super(context, dataList);
    }

    @Override
    public BaseWeiboHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_base_weibo_content, parent, false);
        BaseWeiboHolder holder = new BaseWeiboHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(BaseWeiboHolder holder, int position) {
        Weibo weibo = getItem(position);
        holder.setUserProfile(weibo.getUser().getProfile_image_url());
        holder.setUserName(weibo.getUser().getScreen_name());
        holder.setTimestamp(TimeFormatter.formatTime(weibo.getCreated_at()));
        holder.setContentText(weibo.getText());
    }

    @Override
    public int getItemCount() {
        return getSize();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
}
