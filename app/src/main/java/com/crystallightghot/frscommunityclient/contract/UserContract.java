package com.crystallightghot.frscommunityclient.contract;

import com.crystallightghot.frscommunityclient.view.pojo.system.User;

/**
 * @Date 2022/1/27
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public interface UserContract {
    interface Model {
        void modifyUsername(User user, UserRespondCallBack userRespondCallBack);
    }

    interface View extends BaseContract.View {
    }

    interface Presenter {

        /**
         * 修改用户名
         * @param view
         * @param user
         */
      void modifyUsername(View view , User user);
    }

    interface UserRespondCallBack {

        void modifyUsernameSuccess(String message,Object data);
        void modifyUsernameFailed(String message);
    }
}
