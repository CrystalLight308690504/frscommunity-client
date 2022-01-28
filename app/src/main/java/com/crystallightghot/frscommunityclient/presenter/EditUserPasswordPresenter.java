package com.crystallightghot.frscommunityclient.presenter;

import com.crystallightghot.frscommunityclient.contract.EditUserPasswordContract;
import com.crystallightghot.frscommunityclient.model.EditUserPasswordModel;
import com.crystallightghot.frscommunityclient.utils.EventBusUtil;
import com.crystallightghot.frscommunityclient.utils.XToastUtils;
import com.crystallightghot.frscommunityclient.view.enums.MessageCode;
import com.crystallightghot.frscommunityclient.view.fragment.EditeUserPasswordByOldPasswordFragment;
import com.crystallightghot.frscommunityclient.view.fragment.EditeUserPasswordByPhoneNumberFragment;
import com.crystallightghot.frscommunityclient.view.message.RequestMessage;
import com.crystallightghot.frscommunityclient.view.pojo.system.RequestResult;
import com.crystallightghot.frscommunityclient.view.pojo.system.User;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @Date 2022/1/27
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class EditUserPasswordPresenter implements EditUserPasswordContract.Presenter, EditUserPasswordContract.EditUserPasswordCallBack {

    EditUserPasswordModel model;
    EditeUserPasswordByPhoneNumberFragment editeUserPasswordByPhoneNumberFragment;
    EditeUserPasswordByOldPasswordFragment editeUserPasswordByOldPasswordFragment;

    public EditUserPasswordPresenter() {
    }

    public EditUserPasswordPresenter(EditeUserPasswordByOldPasswordFragment editeUserPasswordByOldPasswordFragment) {
        EventBusUtil.register(this);
        model = new EditUserPasswordModel();
        EventBusUtil.register(this);
        this.editeUserPasswordByOldPasswordFragment = editeUserPasswordByOldPasswordFragment;
    }

    public EditUserPasswordPresenter(EditeUserPasswordByPhoneNumberFragment editeUserPasswordByPhoneNumberFragment) {
        EventBusUtil.register(this);
        model = new EditUserPasswordModel();
        this.editeUserPasswordByPhoneNumberFragment = editeUserPasswordByPhoneNumberFragment;
    }

    public void modifyUserPasswordByPhoneNumber(User user) {
        editeUserPasswordByPhoneNumberFragment.showLoadingDialog();
        model.modifyUserPasswordByPhoneNumber(user, this);
    }


    @Override
    public void modifyUserPasswordByPhoneResult(RequestResult requestResult) {
        RequestMessage message = new RequestMessage(requestResult, MessageCode.MODIFY_PASSWD_BY_PHONE_NUMBER_RESULT);
        EventBusUtil.sendMessage(message);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMessage(RequestMessage message) {
        editeUserPasswordByPhoneNumberFragment.hideLoadingDialog();
        if (message.getMessageCode() != MessageCode.MODIFY_PASSWD_BY_PHONE_NUMBER_RESULT) {
            return;
        }

        if (message.isSuccess()){
            XToastUtils.success("修改成功");
            editeUserPasswordByPhoneNumberFragment.clearDataInput();
        }else {
            XToastUtils.error(message.getMessage());
        }

    }


}
