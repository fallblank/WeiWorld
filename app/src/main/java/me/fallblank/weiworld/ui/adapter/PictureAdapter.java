package me.fallblank.weiworld.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import me.fallblank.weiworld.R;
import me.fallblank.weiworld.bean.Weibo;
import me.fallblank.weiworld.ui.adapter.holder.PictureHolder;

/**
 * Created by fallb on 2017/4/10.
 */

public class PictureAdapter extends BaseAdapter<Weibo.PicUrlsBean, PictureHolder> {

    public PictureAdapter(Context context, List<Weibo.PicUrlsBean> dataList) {
        super(context, dataList);
    }

    @Override
    public PictureHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.view_weibo_image,parent,false);
        PictureHolder holder = new PictureHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(PictureHolder holder, int position) {
        Weibo.PicUrlsBean picUrlsBean = getItem(position);
        holder.setPicture(picUrlsBean.getThumbnail_pic());
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }
}
