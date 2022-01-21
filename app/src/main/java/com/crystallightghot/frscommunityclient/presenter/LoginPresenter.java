package com.crystallightghot.frscommunityclient.presenter;

import com.crystallightghot.frscommunityclient.contract.LoginContract;
import com.crystallightghot.frscommunityclient.contract.RespondCallBck;
import com.crystallightghot.frscommunityclient.model.LoginModel;
import com.crystallightghot.frscommunityclient.utils.requestio.FRSCApplicationContext;
import com.crystallightghot.frscommunityclient.view.message.RequestMessage;
import com.crystallightghot.frscommunityclient.view.pojo.system.User;
import com.google.gson.Gson;
import org.greenrobot.eventbus.EventBus;

/**
 * @Date 2022/1/21
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class LoginPresenter implements LoginContract.Presenter, RespondCallBck {

    LoginModel model;
    LoginContract.View view;

    public static LoginPresenter getInstance(LoginContract.View view) {
        return new LoginPresenter(view);
    }

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
        model = new LoginModel();
    }

    @Override
    public void loadData(User user) {
        view.showLoadingDialog();
        Gson gson = new Gson();
        String userJson = gson.toJson(user);
        model.login(userJson, this);
    }


    @Override
    public void success(String respondMessage, Object respondData) {

        Gson gson = new Gson();
        User user = gson.fromJson(gson.toJson(respondData), User.class);
        RequestMessage<User> message = new RequestMessage();
        message.setMessage(respondMessage);
        message.setData(user);
        message.setCode(view.getMessageCode());

        // 存储User信息
        FRSCApplicationContext.getInstance().setUser(user);

        EventBus.getDefault().post(message);

    }

    @Override
    public void failure(String failureMessage) {
        view.hideLoadingDialog();
        RequestMessage message = new RequestMessage();
        message.setMessage(failureMessage);
        message.setCode(view.getMessageCode());
        message.setSuccess(false);
        EventBus.getDefault().post(message);
    }

}
