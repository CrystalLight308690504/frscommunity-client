package com.crystallightghot.frscommunityclient.presenter;

import com.crystallightghot.frscommunityclient.model.BlogModel;
import com.crystallightghot.frscommunityclient.model.UserModel;
import com.crystallightghot.frscommunityclient.view.fragment.UserInformationFragment;
import com.crystallightghot.frscommunityclient.view.message.RequestMessage;
import com.crystallightghot.frscommunityclient.view.pojo.system.User;
import com.crystallightghot.frscommunityclient.view.pojo.system.UserFollower;
import com.crystallightghot.frscommunityclient.view.util.FRSCApplicationContext;
import com.crystallightghot.frscommunityclient.view.util.FRSCEventBusUtil;
import com.crystallightghot.frscommunityclient.view.util.XToastUtils;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @Date 2022/2/23
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class UserInformationFragmentPresenter {
    UserInformationFragment view;
    BlogModel blogModel;
    UserModel userModel;

    RespondMessageKey checkIfFollowedK = new RespondMessageKey();
    RespondMessageKey showFollowUserCountK = new RespondMessageKey();
    RespondMessageKey followUserK = new RespondMessageKey();
    RespondMessageKey cancelUserK = new RespondMessageKey();
    RespondMessageKey showFollowerCountK = new RespondMessageKey();



    public UserInformationFragmentPresenter(UserInformationFragment view) {
        this.view = view;
        blogModel = new BlogModel();
        userModel = new UserModel();
        FRSCEventBusUtil.register(this);
    }



    public void checkIfFollowed(Long userFollowedId) {
        userModel.checkIfFollowed(FRSCApplicationContext.getUser().getUserId(), userFollowedId, checkIfFollowedK);
    }

    public void loadFollowerCount(Long userId) {
        userModel.loadFollowerCount(userId, showFollowerCountK);
    }

    public void followUser(Long userId) {
        UserFollower userFollower = new UserFollower();
        userFollower.setUserId(FRSCApplicationContext.getUser().getUserId());
        userFollower.setUserFollowedId(userId);
        userModel.followUser(userFollower, followUserK);
    }

    public void cancelFollower(Long userId, Long userFollowedId) {
        userModel.cancelFollower(userId, userFollowedId, cancelUserK);
    }

    public void loadFollowUserCount(Long userId) {
        userModel.loadFollowUserCount(userId,showFollowUserCountK);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMessage(RequestMessage<RespondMessageKey> message) {
        if (message.getMessageKey() == showFollowUserCountK) {
            double data = (double) message.getData();
            long count = (long) data;
            view.showFollowUserCount(count);
        } else if (message.getMessageKey() == followUserK) {
            if (message.isSuccess()) {
                XToastUtils.success(message.getMessage());
            } else {
                XToastUtils.error(message.getMessage());

            }
        } else if (message.getMessageKey() == cancelUserK) {
            if (message.isSuccess()) {
                XToastUtils.success(message.getMessage());
            } else {
                XToastUtils.error(message.getMessage());
            }
        } else if (message.getMessageKey() == showFollowerCountK) {
            if (message.isSuccess()) {
                double data = (double) message.getData();
                int count = (int) data;
                view.showFollowerCount(count);
            }
        } else if (message.getMessageKey() == checkIfFollowedK) {
            if (message.isSuccess()) {
                boolean isFollowed = (boolean) message.getData();
                view.isFollowed(isFollowed);
            }
        }

    }

    private class RespondMessageKey {
    }
}
