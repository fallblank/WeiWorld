package me.fallblank.weiworld.ui.fragment;

import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import me.fallblank.weiworld.R;

/**
 * Created by fallb on 2017/4/21.
 */

public class ContactCommentFragment extends BaseFragment {

    @BindView(R.id.rv_list)
    RecyclerView mCommentList;

    @Override
    public int setLayout() {
        return R.layout.view_recycler;
    }
}
