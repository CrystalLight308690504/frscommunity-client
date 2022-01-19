package com.crystallightghot.frscommunityclient.contract;

import com.crystallightghot.frscommunityclient.view.pojo.system.User;

/**
 * @Date 2022/1/19
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public interface IRegisterContract {
    interface Model {
        void registerUser(User user, final LoadDataCallBack callback);
    }

    interface View {
        void showLoadingDialog();

        void hideLoadingDialog();

        void showRegisterStateMessage(String registerMessage);
    }

    interface Presenter {

        void loadData();
    }

    interface LoadDataCallBack {
        void success(String message);

        void failure();
    }
}
