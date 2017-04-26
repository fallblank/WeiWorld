package me.fallblank.weiworld.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;

import java.util.List;

import me.fallblank.weiworld.bean.BaseBean;
import me.fallblank.weiworld.ui.adapter.holder.weibo.BaseWeiboHolder;

/**
 * Created by fallb on 2017/4/21.
 */

public class MentionAdapter extends BaseAdapter<BaseBean,BaseWeiboHolder> {
    public MentionAdapter(Context context, List<BaseBean> dataList) {
        super(context, dataList);
    }

    @Override
    public BaseWeiboHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(BaseWeiboHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
