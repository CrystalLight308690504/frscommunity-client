package com.crystallightghot.frscommunityclient.presenter;

import com.crystallightghot.frscommunityclient.model.BlogModel;
import com.crystallightghot.frscommunityclient.model.UserModel;
import com.crystallightghot.frscommunityclient.view.fragment.MyFragment;
import com.crystallightghot.frscommunityclient.view.message.RequestMessage;
import com.crystallightghot.frscommunityclient.view.message.UserChangedMessage;
import com.crystallightghot.frscommunityclient.view.util.FRSCApplicationContext;
import com.crystallightghot.frscommunityclient.view.util.FRSCEventBusUtil;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @Date 2022/2/22
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class MyFragmentPresenter {
    MyFragment view;
    UserModel model;
    BlogModel blogModel;

    public MyFragmentPresenter() {

    }

    public MyFragmentPresenter(MyFragment view) {
        this.view = view;
        model = new UserModel();
        blogModel = new BlogModel();
        FRSCEventBusUtil.register(this);
    }

    public void loadFollowUserCount() {
        model.loadFollowUserCount(FRSCApplicationContext.getUser().getUserId(), RespondMessageKey.LOAD_FOLLOW_USER_COUNT);
    }

    public void loadFanOfUserCount() {
        model.loadFanOfUserCount(FRSCApplicationContext.getUser().getUserId(), RespondMessageKey.LOAD_FAN_COUNT);
    }

    public void loadBlogCount() {
        blogModel.loadBlogCount(FRSCApplicationContext.getUser().getUserId(), RespondMessageKey.LOAD_BlOG_COUNT);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMessage(UserChangedMessage message) {
        view.userChanged();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMessage(RequestMessage message) {
        if (!message.getMessageKey().getClass().equals(RespondMessageKey.class)){
            return;
        }
        switch ((RespondMessageKey)message.getMessageKey()) {
            case LOAD_FOLLOW_USER_COUNT:
                if (message.isSuccess()) {
                    double data = (Double) message.getData();
                    view.showFollowUserCount((long) data);
                }
                break;
            case LOAD_FAN_COUNT:
                if (message.isSuccess()) {
                    double data = (Double) message.getData();
                    view.showFanCount((long) data);
                }
                break;
            case LOAD_BlOG_COUNT:
                if (message.isSuccess()) {
                    double count = (Double) message.getData();
                    view.showBlogCount((long) count);
                }
                break;
            case SHOW_APPLAUSE_COUNT:
                if (message.isSuccess()) {
                    double count = (Double) message.getData();
                    view.showApplauseCount((long) count);
                }
                break;
        }

    }

    public void loadApplauseCount() {
        blogModel.loadApplauseCount(FRSCApplicationContext.getUser().getUserId(), RespondMessageKey.SHOW_APPLAUSE_COUNT);
    }

    private enum RespondMessageKey {
        LOAD_FOLLOW_USER_COUNT,
        LOAD_FAN_COUNT,
        LOAD_BlOG_COUNT,
        SHOW_APPLAUSE_COUNT
    }
}
