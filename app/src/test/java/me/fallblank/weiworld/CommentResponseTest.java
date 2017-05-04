package me.fallblank.weiworld;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import me.fallblank.weiworld.bean.Comment;
import me.fallblank.weiworld.bean.CommentResponse;
import me.fallblank.weiworld.biz.retrofit.IComment;
import me.fallblank.weiworld.impl.retrofit.RetrofitCenter;

/**
 * Created by fallb on 2017/5/3.
 */

public class CommentResponseTest {
    IComment mIComment;
    Map<String, String> mQueryMap;
    
    @Before
    public void setup() {
        RetrofitCenter retrofitCenter = new RetrofitCenter();
        mIComment = retrofitCenter.getComment();
        mQueryMap = new HashMap<>();
        mQueryMap.put("access_token", "2.00LsUikF0B2xVN1dc1b07eb8VlqYsB");
    }
    
    @Test
    public void testCommentResponse() {
        mIComment.getComment(mQueryMap)
                .subscribe(new Observer<CommentResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("开始");
                    }
                    
                    @Override
                    public void onNext(CommentResponse commentResponse) {
                        System.out.println("size: " + commentResponse.getComments().size());
                        List<Comment> comments = commentResponse.getComments();
                        for (Comment c : comments) {
                            System.out.println(c.getText());
                            if (c.getReply_comment() != null) {
                                System.out.println(c.getReply_comment().getText());
                            } else {
                                System.out.println(c.getStatus().getText());
                            }
                            System.out.println("*******************");
                        }
                        System.out.println("totalNumber: " + commentResponse.getTotal_number());
                    }
                    
                    @Override
                    public void onError(Throwable e) {
                        System.out.println("出错");
                        e.printStackTrace();
                    }
                    
                    @Override
                    public void onComplete() {
                        System.out.println("完成");
                    }
                });
    }
}
