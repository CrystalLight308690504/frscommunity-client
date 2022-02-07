package com.crystallightghot.frscommunityclient.presenter;

import com.crystallightghot.frscommunityclient.model.BlogModel;
import com.crystallightghot.frscommunityclient.view.adapter.ArticlesAdapter;
import com.crystallightghot.frscommunityclient.view.message.BlogChangMessage;
import com.crystallightghot.frscommunityclient.view.message.RequestMessage;
import com.crystallightghot.frscommunityclient.view.pojo.blog.Blog;
import com.crystallightghot.frscommunityclient.view.util.FRSCEventBusUtil;
import com.crystallightghot.frscommunityclient.view.util.XToastUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @Date 2022/2/7
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class ArticlesAdapterPresenter {
    private ArticlesAdapter articlesAdapter;
    BlogModel model;

    public ArticlesAdapterPresenter(ArticlesAdapter articlesAdapter) {
        this.articlesAdapter = articlesAdapter;
        model = new BlogModel();
        FRSCEventBusUtil.register(this);
    }

    public void deleteBlog(Blog blog) {
        articlesAdapter.showLoadingDialog();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        String blogJson = gson.toJson(blog);
        model.deleteBlog(blogJson, RespondMessageKey.DELETE_BLOG);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMessage(RequestMessage message) {
        if (!(message.getMessageKey() instanceof RespondMessageKey)) {
            return;
        }
        switch ((RespondMessageKey) message.getMessageKey()) {
            case DELETE_BLOG:
                articlesAdapter.hideLoadingDialog();
                if (message.isSuccess()) {
                    XToastUtils.success(message.getMessage());
                    BlogChangMessage blogChangMessage = new BlogChangMessage();
                    FRSCEventBusUtil.sendMessage(blogChangMessage);
                }else {
                    XToastUtils.error(message.getMessage());
                }
        }
    }

    public enum RespondMessageKey {
        DELETE_BLOG
    }

}
