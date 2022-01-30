package com.crystallightghot.frscommunityclient.presenter;

import com.crystallightghot.frscommunityclient.contract.RegisterContract;
import com.crystallightghot.frscommunityclient.contract.RespondCallBck;
import com.crystallightghot.frscommunityclient.model.RegisterModel;
import com.crystallightghot.frscommunityclient.view.message.RegisterMessage;
import com.crystallightghot.frscommunityclient.view.pojo.system.User;
import lombok.Data;
import org.greenrobot.eventbus.EventBus;

/**
 * @Date 2022/1/19
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */

@Data
public class RegisterPresenter implements RegisterContract.Presenter, RespondCallBck {

    RegisterContract.Model model;
    RegisterContract.View view;
    User user;

    public static RegisterPresenter getInstance(RegisterContract.View view) {
        return new RegisterPresenter(view);
    }

    public RegisterPresenter(RegisterContract.View view) {
        this.view = view;
        model = new RegisterModel();
        this.user = user;
    }

    @Override
    public void loadData(User user) {
        this.user = user;
        view.showLoadingDialog();
        model.registerUser(user,this);
    }

    @Override
    public void success(String message,Object respondData) {
        view.hideLoadingDialog();
        RegisterMessage registerMessage = new RegisterMessage();
        registerMessage.setPhoneNumber(user.getPhoneNumber());
        registerMessage.setPassword(user.getPassword());
        registerMessage.setSuccess(true);
        registerMessage.setMessage("注册成功");
        EventBus.getDefault().post(registerMessage);
    }

    @Override
    public void failure(String failMessage) {
        view.hideLoadingDialog();
        RegisterMessage registerMessage = new RegisterMessage();
        registerMessage.setMessage(failMessage);
        registerMessage.setSuccess(false);
        EventBus.getDefault().post(registerMessage);
    }
}
