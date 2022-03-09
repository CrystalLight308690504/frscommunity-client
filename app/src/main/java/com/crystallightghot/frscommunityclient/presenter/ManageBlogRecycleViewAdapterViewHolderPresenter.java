package com.crystallightghot.frscommunityclient.presenter;

import com.crystallightghot.frscommunityclient.model.BlogModel;
import com.crystallightghot.frscommunityclient.view.adapter.ManageBlogRecycleViewAdapter;
import com.crystallightghot.frscommunityclient.view.message.RequestMessage;
import com.crystallightghot.frscommunityclient.view.util.FRSCEventBusUtil;
import com.crystallightghot.frscommunityclient.view.util.XToastUtils;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @Date 2022/3/9
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class ManageBlogRecycleViewAdapterViewHolderPresenter {
    private ManageBlogRecycleViewAdapter.ViewHolder view;
    BlogModel blogModel = new BlogModel();

    public ManageBlogRecycleViewAdapterViewHolderPresenter(ManageBlogRecycleViewAdapter.ViewHolder view) {
        this.view = view;
        FRSCEventBusUtil.register(this);
    }

    public void changIsShowed(long blogId, boolean isShowed) {
        blogModel.changBlogIsShowed(blogId, isShowed, RespondMessageKey.CHANGE_BLOGS_IS_SHOWED);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMessage(RequestMessage message) {
        if (!(message.getMessageKey() instanceof RespondMessageKey)) {
            return;
        }
        switch ((RespondMessageKey) message.getMessageKey()) {
            case CHANGE_BLOGS_IS_SHOWED:
                if (message.isSuccess()) {
                    XToastUtils.success(message.getMessage());
                }else {
                    XToastUtils.error(message.getMessage());
                }
        }
    }

    enum RespondMessageKey {
        CHANGE_BLOGS_IS_SHOWED,
    }
}
