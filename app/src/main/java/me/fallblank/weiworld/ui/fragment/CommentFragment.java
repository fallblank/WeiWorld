package me.fallblank.weiworld.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import me.fallblank.weiworld.R;
import me.fallblank.weiworld.bean.Comment;
import me.fallblank.weiworld.iview.ILoadView;
import me.fallblank.weiworld.iview.IView;
import me.fallblank.weiworld.presenter.CommentsPresenter;
import me.fallblank.weiworld.ui.adapter.CommentAdapter;

import static com.sina.weibo.sdk.openapi.legacy.CommonAPI.CAPITAL.g;
import static com.sina.weibo.sdk.openapi.legacy.CommonAPI.CAPITAL.m;

/**
 * Created by fallb on 2017/4/20.
 */

public class CommentFragment extends BaseFragment implements ILoadView {

    public static final String ARG_WEIBO_ID = "weibo.id";

    @BindView(R.id.rv_list)
    RecyclerView mCommnetList;

    RecyclerView.LayoutManager mLayout;
    CommentAdapter mCommentAdapter;
    List<Comment> mDataList;
    private CommentsPresenter mPresenter;
    private long mWeiboId;

    public static CommentFragment newInstance(long id) {
        CommentFragment fragment = new CommentFragment();
        // TODO: 2017/4/20 init bundle
        Bundle bundle = new Bundle();
        bundle.putLong(ARG_WEIBO_ID, id);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int setLayout() {
        return R.layout.fragment_comment_list;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        mWeiboId = bundle.getLong(ARG_WEIBO_ID);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = super.onCreateView(inflater, container, savedInstanceState);
        init();
        return root;
    }

    private void init() {
        mDataList = new LinkedList<>();
        mCommentAdapter = new CommentAdapter(mContext, mDataList);
        mLayout = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        mCommnetList.setLayoutManager(mLayout);
        mCommnetList.setAdapter(mCommentAdapter);
        mPresenter = new CommentsPresenter(mContext, this, mDataList, mWeiboId);
        mPresenter.load();
    }

    @Override
    public void onStartLoad() {

    }

    @Override
    public void onLoadComplete() {
        mCommentAdapter.notifyDataSetChanged();
    }
}
