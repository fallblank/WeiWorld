package me.fallblank.weiworld.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.openapi.legacy.FavoritesAPI;
import com.sina.weibo.sdk.openapi.legacy.FriendshipsAPI;

import me.fallblank.weiworld.App;
import me.fallblank.weiworld.BuildConfig;
import me.fallblank.weiworld.R;
import me.fallblank.weiworld.bean.User;
import me.fallblank.weiworld.bean.Weibo;
import me.fallblank.weiworld.util.ToastUtil;

public class WeiboOptionBottomSheetFragment extends BottomSheetDialogFragment {
    
    private static final String ARG_ITEM = "item";
    
    
    public static WeiboOptionBottomSheetFragment newInstance(Weibo item) {
        final WeiboOptionBottomSheetFragment fragment = new WeiboOptionBottomSheetFragment();
        final Bundle args = new Bundle();
        args.putSerializable(ARG_ITEM, item);
        fragment.setArguments(args);
        return fragment;
    }
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_item_list_dialog, container, false);
    }
    
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        final RecyclerView recyclerView = (RecyclerView) view;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new ItemAdapter((Weibo) getArguments().getSerializable(ARG_ITEM)));
    }
    
    
    private class ViewHolder extends RecyclerView.ViewHolder {
        
        final TextView text;
        
        ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            // TODO: Customize the item layout
            super(inflater.inflate(R.layout.item_weibo_option, parent, false));
            text = (TextView) itemView.findViewById(R.id.text);
        }
        
    }
    
    private class ItemAdapter extends RecyclerView.Adapter<ViewHolder> {
        private final String[] sOptionTextArray = {
                "收藏",
                "取消收藏",
                "关注",
                "取消关注"
        };
        private final Weibo mItem;
        
        ItemAdapter(Weibo item) {
            this.mItem = item;
        }
        
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }
        
        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            holder.text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Context context = v.getContext();
                    App app = (App) context.getApplicationContext();
                    if (position == 0) {
                        FavoritesAPI favoritesAPI = new FavoritesAPI(context, BuildConfig.APP_KEY, app.getAccessToken());
                        if (mItem.isFavorited()) {
                            favoritesAPI.destroy(mItem.getId(), new RequestListener() {
                                @Override
                                public void onComplete(String s) {
                                    ToastUtil.show(context, "取消收藏成功");
                                    mItem.setFavorited(false);
                                }
                                
                                @Override
                                public void onWeiboException(WeiboException e) {
                                    ToastUtil.show(context, "取消收藏失败");
                                }
                            });
                        } else {
                            favoritesAPI.create(mItem.getId(), new RequestListener() {
                                @Override
                                public void onComplete(String s) {
                                    ToastUtil.show(context, "收藏成功");
                                    mItem.setFavorited(true);
                                }
                                
                                @Override
                                public void onWeiboException(WeiboException e) {
                                    ToastUtil.show(context, "收藏失败");
                                }
                            });
                        }
                    }
                    if (position == 1){
                        FriendshipsAPI friendshipsAPI = new FriendshipsAPI(context,BuildConfig.APP_KEY,app.getAccessToken());
                        final User user = mItem.getUser();
                        if (user.isFollowing()){
                            friendshipsAPI.destroy(user.getId(), user.getScreen_name(), new RequestListener() {
                                @Override
                                public void onComplete(String s) {
                                    ToastUtil.show(context,"取消关注成功");
                                    user.setFollowing(false);
                                }
    
                                @Override
                                public void onWeiboException(WeiboException e) {
                                    ToastUtil.show(context,"取消关注失败");
                                }
                            });
                        }else{
                            friendshipsAPI.create(user.getId(), user.getScreen_name(), new RequestListener() {
                                @Override
                                public void onComplete(String s) {
                                    ToastUtil.show(context,"关注成功");
                                    user.setFollowing(true);
                                }
    
                                @Override
                                public void onWeiboException(WeiboException e) {
                                    ToastUtil.show(context,"关注失败");
                                }
                            });
                        }
                    }
                    
                    
                    dismiss();
                }
            });
            if (position == 0 && !mItem.isFavorited()) {
                holder.text.setText(sOptionTextArray[0]);
            }
            if (position == 0 && mItem.isFavorited()) {
                holder.text.setText(sOptionTextArray[1]);
            }
            if (position == 1 && !mItem.getUser().isFollowing()) {
                holder.text.setText(sOptionTextArray[2]);
            }
            if (position == 1 && mItem.getUser().isFollowing()) {
                holder.text.setText(sOptionTextArray[3]);
            }
        }
        
        @Override
        public int getItemCount() {
            return 2;
        }
        
    }
    
}
