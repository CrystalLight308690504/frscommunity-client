package com.crystallightghot.frscommunityclient.presenter;

import com.crystallightghot.frscommunityclient.model.BlogModel;
import com.crystallightghot.frscommunityclient.model.UserModel;
import com.crystallightghot.frscommunityclient.view.fragment.UserInformationFragment;
import com.crystallightghot.frscommunityclient.view.message.RequestMessage;
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


    public UserInformationFragmentPresenter(UserInformationFragment view) {
        this.view = view;
        blogModel = new BlogModel();
        userModel = new UserModel();
        FRSCEventBusUtil.register(this);
    }


    public void checkIfFollowed(Long userFollowedId) {
        userModel.checkIfFollowed(FRSCApplicationContext.getUser().getUserId(), userFollowedId, RespondMessageKey.checkIfFollowedK);
    }

    public void loadFollowerCount(Long userId) {
        userModel.loadFollowerCount(userId, RespondMessageKey.showFollowerCountK);
    }

    public void followUser(Long userId) {
        UserFollower userFollower = new UserFollower();
        userFollower.setUserId(FRSCApplicationContext.getUser().getUserId());
        userFollower.setUserFollowedId(userId);
        userModel.followUser(userFollower, RespondMessageKey.followUserK);
    }

    public void cancelFollower(Long userId, Long userFollowedId) {
        userModel.cancelFollower(userId, userFollowedId, RespondMessageKey.cancelUserK);
    }

    public void loadApplauseCount(Long userId) {
        blogModel.loadApplauseCount(userId, RespondMessageKey.showApplauseCount);

    }

    public void loadFollowUserCount(Long userId) {
        userModel.loadFollowUserCount(userId, RespondMessageKey.showFollowUserCountK);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMessage(RequestMessage<RespondMessageKey> message) {
        switch (message.getMessageKey()) {
            case cancelUserK:
            case followUserK:
                if (message.isSuccess()) {
                    XToastUtils.success(message.getMessage());
                } else {
                    XToastUtils.error(message.getMessage());
                }
                break;
            case showFollowUserCountK:
                double data = (double) message.getData();
                long count = (long) data;
                view.showFollowUserCount(count);
                break;
            case showFollowerCountK:
                if (message.isSuccess()) {
                    double data0 = (double) message.getData();
                    int count0 = (int) data0;
                    view.showFollowerCount(count0);
                }
                break;
            case checkIfFollowedK:
                if (message.isSuccess()) {
                    boolean isFollowed = (boolean) message.getData();
                    view.isFollowed(isFollowed);
                }
                break;
            case showApplauseCount:
                if (message.isSuccess()) {
                    double count1 = (double) message.getData();
                    view.showApplauseCount((long) count1);
                }
                break;

        }

    }

    private enum RespondMessageKey {
        checkIfFollowedK,
        showFollowUserCountK,
        followUserK,
        cancelUserK,
        showFollowerCountK,
        showApplauseCount,
    }
}
