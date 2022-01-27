package com.crystallightghot.frscommunityclient.contract;

import com.crystallightghot.frscommunityclient.view.pojo.system.User;

/**
 * @Date 2022/1/19
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public interface RegisterContract {
    interface Model extends BaseContract.Model{
        void registerUser(User user, final RespondCallBck callback);
    }

    interface View extends BaseContract.View {
    }

    interface Presenter extends BaseContract.Presenter {

        void loadData(User user);
    }

}
