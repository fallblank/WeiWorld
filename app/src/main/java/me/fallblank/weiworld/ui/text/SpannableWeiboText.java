package me.fallblank.weiworld.ui.text;

import android.support.v7.app.AlertDialog;
import android.text.SpannableStringBuilder;

/**
 * Created by fallb on 2017/4/11.
 * 解析微博富文本
 * TODO：需要达到以下目标
 * - @
 * - [emoj]
 * - #话题#
 * - http://
 * <p>
 * TODO:存在丢失识别情况
 */

public class SpannableWeiboText {

    private BaseText mBaseText;

    /**
     * 屏蔽构造方法，强制使用构建器模式
     */
    private SpannableWeiboText(CharSequence text) {
        mBaseText = new BaseText(text);
    }

    public SpannableStringBuilder getStringBuilder() {
        return mBaseText;
    }

    public static class Builder {
        private boolean mAt = false;
        private boolean mTopic = false;

        public Builder loadAt() {
            this.mAt = true;
            return this;
        }

        public Builder loadTopic() {
            this.mTopic = true;
            return this;
        }

        public SpannableWeiboText build(CharSequence source) {
            SpannableWeiboText instance = new SpannableWeiboText(source);
            if (mAt) {
                AtText atText = new AtText(instance.mBaseText);
                instance.mBaseText = atText;
            }
            if (mTopic) {
                TopicText topicText = new TopicText(instance.mBaseText);
                instance.mBaseText = topicText;
            }
            return instance;
        }
    }


}
