package me.fallblank.weiworld.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import me.fallblank.weiworld.R;
import me.fallblank.weiworld.bean.Weibo;
import me.fallblank.weiworld.bean.WeiboType;
import me.fallblank.weiworld.ui.adapter.holder.BaseWeiboHolder;
import me.fallblank.weiworld.ui.adapter.holder.PictureWeiboHolder;
import me.fallblank.weiworld.ui.adapter.holder.TextWeiboHolder;
import me.fallblank.weiworld.ui.adapter.holder.RetPictureWeiboHolder;
import me.fallblank.weiworld.ui.adapter.holder.RetTextWeiboHolder;
import me.fallblank.weiworld.ui.adapter.holder.RetWeiboHolder;
import me.fallblank.weiworld.ui.text.SpannableWeiboText;
import me.fallblank.weiworld.util.TimeFormatter;

import static com.sina.weibo.sdk.openapi.legacy.CommonAPI.CAPITAL.r;

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
        BaseWeiboHolder holder = null;
        switch (viewType) {
            case WeiboType.ORIGINAL:
                holder = new BaseWeiboHolder(itemView);
                break;
            case WeiboType.ORIGINAL_PICTURE:
                holder = new PictureWeiboHolder(itemView);
                break;
            case WeiboType.ORIGINAL_PLAIN_TEXT:
                holder = new TextWeiboHolder(itemView);
                break;
            case WeiboType.RETWEET:
                holder = new RetWeiboHolder(itemView);
                break;
            case WeiboType.RETWEET_PICTURE:
                holder = new RetPictureWeiboHolder(itemView);
                break;
            case WeiboType.RETWEET_PLAIN_TEXT:
                holder = new RetTextWeiboHolder(itemView);
                break;
            default:
                holder = new BaseWeiboHolder(itemView);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(BaseWeiboHolder holder, int position) {
        Weibo weibo = getItem(position);
        holder.setUserProfile(weibo.getUser().getProfile_image_url());
        holder.setUserName(weibo.getUser().getScreen_name());
        holder.setTimestamp(TimeFormatter.formatTime(weibo.getCreated_at()));
        SpannableWeiboText originTextBuilder = new SpannableWeiboText.Builder()
                .loadAt()
                .loadTopic()
                .build(weibo.getText());
        holder.setContentText(originTextBuilder.getStringBuilder());

        int type = getItemViewType(position);
        if (type / 100 == WeiboType.TYPE_RETWEET) {
            RetWeiboHolder retweetHolder = (RetWeiboHolder) holder;
            Weibo retweetWeibo = weibo.getRetweeted_status();
            CharSequence retweetText = "@" + retweetWeibo.getUser().getScreen_name() + " :"
                    + retweetWeibo.getText();
            SpannableWeiboText weiboText = new SpannableWeiboText.Builder()
                    .loadAt()
                    .loadTopic()
                    .build(retweetText);
            retweetHolder.setRetweetText(weiboText.getStringBuilder());
        }

        Weibo retweetWeibo = null;
        switch (type) {
            case WeiboType.ORIGINAL:
                //empty
                break;
            case WeiboType.ORIGINAL_PICTURE:
                PictureWeiboHolder pictureboHolder = (PictureWeiboHolder) holder;
                pictureboHolder.setPictureList(weibo.getPic_urls());
                break;
            case WeiboType.ORIGINAL_PLAIN_TEXT:
                //empty
                break;
            case WeiboType.RETWEET:
                //empty
                break;
            case WeiboType.RETWEET_PICTURE:
                retweetWeibo = weibo.getRetweeted_status();
                RetPictureWeiboHolder retweetPictureHolder = (RetPictureWeiboHolder) holder;
                retweetPictureHolder.setPictureList(retweetWeibo.getPic_urls());
                break;
            case WeiboType.RETWEET_PLAIN_TEXT:
                //empty
                break;
            default:
                //empty
                break;
        }
    }

    @Override
    public int getItemCount() {
        return getSize();
    }

    @Override
    public int getItemViewType(int position) {
        Weibo weibo = getItem(position);
        if (weibo.isRetweet()) {
            Weibo retweetWeibo = weibo.getRetweeted_status();
            if (retweetWeibo.isContainPic()) {
                return WeiboType.RETWEET_PICTURE;
            }
            return WeiboType.RETWEET;
        }
        if (weibo.isContainPic()) {
            return WeiboType.ORIGINAL_PICTURE;
        }
        return WeiboType.ORIGINAL;
    }
}
