package com.crystallightghot.frscommunityclient.presenter;

import com.crystallightghot.frscommunityclient.model.BlogModel;
import com.crystallightghot.frscommunityclient.model.UserModel;
import com.crystallightghot.frscommunityclient.view.adapter.UserSearchResultRecyclerViewAdapter;
import com.crystallightghot.frscommunityclient.view.message.RequestMessage;
import com.crystallightghot.frscommunityclient.view.pojo.system.UserFollower;
import com.crystallightghot.frscommunityclient.view.util.FRSCApplicationContext;
import com.crystallightghot.frscommunityclient.view.util.FRSCEventBusUtil;
import com.crystallightghot.frscommunityclient.view.util.XToastUtils;
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
    BlogModel blogModel;
    UserModel userModel;
    RespondMessageKey loadBlogCount = new RespondMessageKey();
    RespondMessageKey followUserK = new RespondMessageKey();

    public UserSearchResultRecyclerViewAdapterViewHolderPresenter(UserSearchResultRecyclerViewAdapter.MyViewHolder view) {
        this.view = view;
        blogModel = new BlogModel();
        userModel = new UserModel();
        FRSCEventBusUtil.register(this);
    }


    public void loadBlogCount(Long userId) {
        blogModel.loadBlogCount(userId, loadBlogCount);
    }

    public void followUser(Long userId) {
        UserFollower userFollower = new UserFollower();
        userFollower.setUserId(FRSCApplicationContext.getUser().getUserId());
        userFollower.setUserFollowedId(userId);
        userModel.followUser(userFollower, followUserK);
    }


    private class RespondMessageKey {
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMessage(RequestMessage<RespondMessageKey> message) {
        if (message.getMessageKey() == loadBlogCount) {
            double data = (double) message.getData();
            int count = (int) data;
            view.showArticleCount(count + "");
        } else if (message.getMessageKey() == followUserK) {
            if (message.isSuccess()) {
                XToastUtils.success(message.getMessage());
            }else {
                XToastUtils.error(message.getMessage());

            }
        }

    }

}
