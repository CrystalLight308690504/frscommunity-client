package com.crystallightghot.frscommunityclient.presenter;

import com.crystallightghot.frscommunityclient.contract.UserContract;
import com.crystallightghot.frscommunityclient.model.UserModel;
import com.crystallightghot.frscommunityclient.view.pojo.system.User;

/**
 * @Date 2022/1/27
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class UserPresenter implements UserContract.Presenter , UserContract.UserRespondCallBack {

    UserContract.View view;
    UserContract.Model model;

    public UserPresenter(UserContract.View view) {
        this.view = view;
        this.model = new UserModel();
    }



    public void modifyUsernameExited(UserContract.View view, User user) {
    }

    public void modifyUsernameExitedSuccess(String message, Object data) {

    }

    public void modifyUsernameExitedFailed(String message) {

    }


    @Override
    public void modifyUsername(UserContract.View view, User user) {
        view.showLoadingDialog();
        model.modifyUsername(user,this);
    }
    @Override
    public void modifyUsernameSuccess(String message, Object data) {

    }

    @Override
    public void modifyUsernameFailed(String message) {

    }
}
