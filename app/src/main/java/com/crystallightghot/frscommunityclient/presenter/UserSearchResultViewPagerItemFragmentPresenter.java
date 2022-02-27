package com.crystallightghot.frscommunityclient.presenter;

import com.crystallightghot.frscommunityclient.model.UserModel;
import com.crystallightghot.frscommunityclient.view.fragment.UserSearchResultViewPagerItemFragment;
import com.crystallightghot.frscommunityclient.view.message.RequestMessage;
import com.crystallightghot.frscommunityclient.view.pojo.system.User;
import com.crystallightghot.frscommunityclient.view.util.FRSCEventBusUtil;
import com.crystallightghot.frscommunityclient.view.util.FRSCGsonUtil;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Date 2022/2/21
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class UserSearchResultViewPagerItemFragmentPresenter {
    UserSearchResultViewPagerItemFragment view;
    UserModel model;
    boolean hasNext = true;

    public UserSearchResultViewPagerItemFragmentPresenter(UserSearchResultViewPagerItemFragment view) {
        this.view = view;
        model = new UserModel();
        FRSCEventBusUtil.register(this);
    }

    public void loadSearchUserResult(String searchText, int pagerIndex) {
        model.findUserByNameKey(searchText, pagerIndex,RespondMessageKey.LOADING_USERS_BY_NAME_KEY);
    }

    private enum RespondMessageKey {
        LOADING_USERS_BY_NAME_KEY
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMessage(RequestMessage message) {
        if (!message.getMessageKey().getClass().equals(RespondMessageKey.class)){
            return;
        }
        switch ((RespondMessageKey)message.getMessageKey()) {
            case LOADING_USERS_BY_NAME_KEY:
                if (message.isSuccess()) {
                    Map resultMap = (Map) message.getData();
                    ArrayList dataList = (ArrayList) resultMap.get("data");
                    List<User> users = FRSCGsonUtil.listMapToListObject(dataList, User.class);
                    hasNext = (boolean) resultMap.get("hasNext");
                    view.addDataToRV(users, hasNext);
                }else {
                    view.showError(message.getMessage());
                }

                break;
        }

    }
}
