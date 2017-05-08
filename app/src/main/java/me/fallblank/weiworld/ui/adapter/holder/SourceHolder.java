package me.fallblank.weiworld.ui.adapter.holder;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import me.fallblank.weiworld.R;
import me.fallblank.weiworld.bean.SourceBean;

/**
 * Created by fallb on 2017/5/8.
 */

public class SourceHolder extends BaseHolder {
    @BindView(R.id.name)
    TextView mName;
    @BindView(R.id.author)
    TextView mAuthor;
    @BindView(R.id.license)
    TextView mLicense;
    
    public SourceHolder(View itemView) {
        super(itemView);
    }
    
    public void setContetn(final SourceBean bean) {
        mName.setText(bean.getName());
        mName.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        mName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri uri = Uri.parse(bean.getUrl());
                intent.setData(uri);
                mContext.startActivity(intent);
            }
        });
        mAuthor.setText(bean.getAuthor());
        mLicense.setText(bean.getLicense());
    }
    
}
