package me.fallblank.weiworld.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import me.fallblank.weiworld.R;
import me.fallblank.weiworld.bean.Weibo;
import me.fallblank.weiworld.ui.adapter.holder.PictureGallery;
import me.fallblank.weiworld.ui.fragment.ImageFragment;
import me.fallblank.weiworld.ui.fragment.PictureOptionBottomSheetFragment;

public class PictureGalleryActivity extends BaseActivity {
    
    
    @BindView(R.id.title_hint)
    TextView mTitleHint;
    
    @BindView(R.id.option)
    View mOption;
    
    @BindView(R.id.vp_pager)
    ViewPager mViewPager;
    
    private Weibo mWeibo;
    private ImageAdaper mAdaper;
    
    @Override
    protected int setContentView() {
        return R.layout.activity_picture_gallery;
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Intent intent = getIntent();
        this.mWeibo = (Weibo) intent.getSerializableExtra(PictureGallery.KEY_WEIBO);
        int position = intent.getIntExtra(PictureGallery.KEY_POSITION, 0);
        mTitleHint.setText((position + 1) + "/" + mWeibo.getOrigin_urls().size());
        mAdaper = new ImageAdaper(getSupportFragmentManager());
        mViewPager.setAdapter(mAdaper);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //empty
            }
            
            @Override
            public void onPageSelected(int position) {
                int count = mAdaper.getCount();
                String hint = (position + 1) + "/" + count;
                mTitleHint.setText(hint);
            }
            
            @Override
            public void onPageScrollStateChanged(int state) {
                //empty
            }
        });
        mViewPager.setCurrentItem(position);
        mOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = mViewPager.getCurrentItem();
                PictureOptionBottomSheetFragment fragment = PictureOptionBottomSheetFragment.newInstance(position, mWeibo);
                fragment.show(getSupportFragmentManager(), "PictureOptionBottomSheetFragment");
            }
        });
    }
    
    class ImageAdaper extends FragmentPagerAdapter {
        
        public ImageAdaper(FragmentManager fm) {
            super(fm);
        }
        
        @Override
        public Fragment getItem(int position) {
            return ImageFragment.newInstance(mWeibo.getOrigin_urls().get(position).getThumbnail_pic());
        }
        
        @Override
        public int getCount() {
            return mWeibo.getOrigin_urls().size();
        }
    }
    
    
}
