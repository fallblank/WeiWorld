package me.fallblank.weiworld.ui.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

import static com.sina.weibo.sdk.openapi.legacy.AccountAPI.CAPITAL.S;

/**
 * Created by fallb on 2017/4/18.
 */

public abstract class BaseFragment extends Fragment {
    protected Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }


    protected abstract int setLayout();

    protected void setTitle(CharSequence title) {
        getActivity().setTitle(title);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(setLayout(), container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public Context getContext() {
        return mContext;
    }
}
