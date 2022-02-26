package com.crystallightghot.frscommunityclient.presenter;

import com.crystallightghot.frscommunityclient.model.BlogModel;
import com.crystallightghot.frscommunityclient.view.adapter.BlogCriticismAdapter;
import com.crystallightghot.frscommunityclient.view.message.RequestMessage;
import com.crystallightghot.frscommunityclient.view.pojo.blog.BlogCriticism;
import com.crystallightghot.frscommunityclient.view.util.FRSCEventBusUtil;
import com.crystallightghot.frscommunityclient.view.util.XToastUtils;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @Date 2022/2/26
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class BlogCriticismAdapterViewHolderPresenter {
    private BlogCriticismAdapter.ViewHolder view;
    BlogModel blogModel;

    RespondMessageKey deleteBlogCriticiseK = new RespondMessageKey();

    public BlogCriticismAdapterViewHolderPresenter(BlogCriticismAdapter.ViewHolder view) {
        this.view = view;
        blogModel = new BlogModel();
        FRSCEventBusUtil.register(this);
    }

    public void deleteBlogCriticise(BlogCriticism blogCriticism) {
        blogModel.deleteBlogCriticise(blogCriticism, deleteBlogCriticiseK);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMessage(RequestMessage message) {
        if (!message.getMessageKey().getClass().equals(RespondMessageKey.class)) {
            return;
        }
        if (message.getMessageKey() == deleteBlogCriticiseK) {
            if (message.isSuccess()) {
                XToastUtils.success(message.getMessage());
                view.deleteBlogCriticiseSuccess();
            }else {
                XToastUtils.error(message.getMessage());
            }
        }

    }

    private class RespondMessageKey {
    }
}
