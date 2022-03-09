package com.crystallightghot.frscommunityclient.presenter;

import com.crystallightghot.frscommunityclient.model.UserModel;
import com.crystallightghot.frscommunityclient.view.fragment.ManageUserFragment;
import com.crystallightghot.frscommunityclient.view.message.RequestMessage;
import com.crystallightghot.frscommunityclient.view.pojo.system.User;
import com.crystallightghot.frscommunityclient.view.util.FRSCEventBusUtil;
import com.crystallightghot.frscommunityclient.view.util.FRSCGsonUtil;
import com.crystallightghot.frscommunityclient.view.util.XToastUtils;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;
import java.util.Map;

/**
 * @Date 2022/3/9
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class ManageUserFragmentPresenter {
    private ManageUserFragment view;
    UserModel userModel = new UserModel();

    public ManageUserFragmentPresenter(ManageUserFragment view) {
        this.view = view;
        FRSCEventBusUtil.register(this);
    }

    public void loadUsers(int pagerIndex) {
        userModel.loadUsers(pagerIndex,RespondMessageKey.LOAD_USERS);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMessage(RequestMessage message) {
        if (!(message.getMessageKey() instanceof RespondMessageKey)) {
            return;
        }
        switch ((RespondMessageKey)message.getMessageKey()) {
            case LOAD_USERS:
                if (message.isSuccess()) {
                    Map data = (Map) message.getData();
                    boolean hasNext = (boolean) data.get("hasNext");
                    List<Map> lists = (List) data.get("data");
                    List<User> users = FRSCGsonUtil.listMapToListObject(lists, User.class);
                    view.showMoreUsers(users, hasNext);
                }else {
                    XToastUtils.error(message.getMessage());
                }
                break;
        }

    }
    enum RespondMessageKey {
        LOAD_USERS,
    }
}
