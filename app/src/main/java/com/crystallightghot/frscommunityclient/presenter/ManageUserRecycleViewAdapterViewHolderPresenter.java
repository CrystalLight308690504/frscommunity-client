package com.crystallightghot.frscommunityclient.presenter;

import com.crystallightghot.frscommunityclient.model.UserModel;
import com.crystallightghot.frscommunityclient.view.adapter.ManageUserRecycleViewAdapter;
import com.crystallightghot.frscommunityclient.view.message.RequestMessage;
import com.crystallightghot.frscommunityclient.view.util.FRSCApplicationContext;
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
public class ManageUserRecycleViewAdapterViewHolderPresenter {
    UserModel model = new UserModel();
    private ManageUserRecycleViewAdapter.ViewHolder view;

    public ManageUserRecycleViewAdapterViewHolderPresenter(ManageUserRecycleViewAdapter.ViewHolder view) {
        FRSCEventBusUtil.register(this);
        this.view = view;
    }

    public void changeRole(long userId ,long roleId) {
        model.changeRole(userId, roleId, RespondMessageKey.CHANGE_ROLE);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMessage(RequestMessage message) {
        if (!(message.getMessageKey() instanceof RespondMessageKey)) {
            return;
        }
        switch ((RespondMessageKey) message.getMessageKey()) {
            case CHANGE_ROLE:
                if (message.isSuccess()) {
                    XToastUtils.success(message.getMessage());
                } else {
                    XToastUtils.error(message.getMessage());
                }
                break;
        }

    }

    enum RespondMessageKey {
        CHANGE_ROLE,
    }
}
