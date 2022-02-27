package com.crystallightghot.frscommunityclient.presenter;

import com.crystallightghot.frscommunityclient.model.UserModel;
import com.crystallightghot.frscommunityclient.view.fragment.MyUserFollowedFragment;
import com.crystallightghot.frscommunityclient.view.message.RequestMessage;
import com.crystallightghot.frscommunityclient.view.pojo.system.UserFollowerEntity;
import com.crystallightghot.frscommunityclient.view.util.FRSCApplicationContext;
import com.crystallightghot.frscommunityclient.view.util.FRSCEventBusUtil;
import com.crystallightghot.frscommunityclient.view.util.FRSCObjectTransferUtil;
import com.crystallightghot.frscommunityclient.view.util.XToastUtils;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;
import java.util.Map;

/**
 * @Date 2022/2/27
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class MyUserFollowedFragmentPresenter {
    private MyUserFollowedFragment view;
    UserModel userModel;


    public MyUserFollowedFragmentPresenter(MyUserFollowedFragment view) {
        this.view = view;
        userModel = new UserModel();
        FRSCEventBusUtil.register(this);
    }

    public void loadUsersFollowed(int pagerIndex) {
        userModel.loadUsersFollowed(FRSCApplicationContext.getUser().getUserId(),pagerIndex, RespondMessageKey.LOAD_USERS_FOLLOWED);
    }

    public void loadUsersFan(int pagerIndex) {
        userModel.loadUsersFollowed(FRSCApplicationContext.getUser().getUserId(), pagerIndex, RespondMessageKey.LOAD_USERS_FAN);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMessage(RequestMessage message) {
        if (!message.getMessageKey().getClass().equals(RespondMessageKey.class)){
            return;
        }
        switch ((RespondMessageKey)message.getMessageKey()){
            case LOAD_USERS_FOLLOWED:
                if (message.isSuccess()) {
                    Map mapResult = (Map) message.getData();
                    boolean hasNext = (boolean) mapResult.get("hasNext");
                    List data = (List) mapResult.get("data");
                    List<UserFollowerEntity> userFollowers = FRSCObjectTransferUtil.listMapToListObject(data, UserFollowerEntity.class);
                    view.loadMoreData(userFollowers, hasNext);
                }else {
                    XToastUtils.error(message.getMessage());
                }
                break;
        }

    }

    enum RespondMessageKey{
        LOAD_USERS_FOLLOWED,
        LOAD_USERS_FAN
    }
}
