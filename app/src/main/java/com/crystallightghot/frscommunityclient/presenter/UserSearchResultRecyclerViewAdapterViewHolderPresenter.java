package com.crystallightghot.frscommunityclient.presenter;

import com.crystallightghot.frscommunityclient.model.BlogModel;
import com.crystallightghot.frscommunityclient.view.adapter.UserSearchResultRecyclerViewAdapter;
import com.crystallightghot.frscommunityclient.view.message.RequestMessage;
import com.crystallightghot.frscommunityclient.view.util.FRSCEventBusUtil;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @Date 2022/2/21
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class UserSearchResultRecyclerViewAdapterViewHolderPresenter {
    UserSearchResultRecyclerViewAdapter.MyViewHolder view;
    BlogModel model;
    RespondMessageKey loadBlogCount = new RespondMessageKey();
    public UserSearchResultRecyclerViewAdapterViewHolderPresenter(UserSearchResultRecyclerViewAdapter.MyViewHolder view) {
        this.view = view;
        model = new BlogModel();
        FRSCEventBusUtil.register(this);
    }


    public void loadBlogCount(Long userId) {
        model.loadBlogCount(userId, loadBlogCount);
    }

    public class RespondMessageKey {
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMessage(RequestMessage<RespondMessageKey> message) {
        if (message.getMessageKey() == loadBlogCount) {
            double data = (double) message.getData();
            int count = (int) data;
            view.showArticleCount(count + "");
        }

    }

}
