package com.crystallightghot.frscommunityclient.presenter;

import com.crystallightghot.frscommunityclient.contract.IRegisterContract;
import com.crystallightghot.frscommunityclient.model.RegisterModel;
import com.crystallightghot.frscommunityclient.view.messageEvent.RegisterMessage;
import com.crystallightghot.frscommunityclient.view.pojo.system.Result;
import com.crystallightghot.frscommunityclient.view.pojo.system.User;
import com.google.gson.Gson;
import lombok.Data;
import org.greenrobot.eventbus.EventBus;

/**
 * @Date 2022/1/19
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */

@Data
public class RegisterPresenter implements IRegisterContract.Presenter, IRegisterContract.LoadDataCallBack {

    IRegisterContract.Model model;
    IRegisterContract.View view;
    User user;

    public RegisterPresenter(IRegisterContract.View view, User user) {
        this.view = view;
        model = new RegisterModel();
        this.user = user;
    }

    @Override
    public void loadData() {
        view.showLoadingDialog();
        model.registerUser(user,this);
    }

    @Override
    public void success(String message) {
        view.hideLoadingDialog();
        view.showRegisterStateMessage(message);

        Gson gson = new Gson();
        Result result = gson.fromJson(message, Result.class);
        RegisterMessage registerMessage = new RegisterMessage();
        registerMessage.setPhoneNumber(user.getPhoneNumber());
        registerMessage.setPassword(user.getPassword());
        registerMessage.setMessage(result.getMessage());
        EventBus.getDefault().post(registerMessage);
    }

    @Override
    public void failure() {
        view.hideLoadingDialog();
    }
}
