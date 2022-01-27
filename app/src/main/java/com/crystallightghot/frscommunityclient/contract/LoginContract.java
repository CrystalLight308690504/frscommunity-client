package com.crystallightghot.frscommunityclient.contract;

import com.crystallightghot.frscommunityclient.view.pojo.system.User;

/**
 * @Date 2022/1/21
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public interface LoginContract  {
    interface Model extends BaseContract.Model {
    }

    interface View extends BaseContract.View {
        int getMessageCode();
        void stateToLogin();
    }

    interface Presenter extends BaseContract.Presenter {
        void login(User user);
    }
}
