package com.crystallightghot.frscommunityclient.presenter;

import com.crystallightghot.frscommunityclient.contract.EditUserPasswordContract;
import com.crystallightghot.frscommunityclient.model.EditUserPasswordModel;
import com.crystallightghot.frscommunityclient.view.util.FRSCEventBusUtil;
import com.crystallightghot.frscommunityclient.view.util.XToastUtils;
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

    public EditUserPasswordPresenter(EditeUserPasswordByOldPasswordFragment editeUserPasswordByOldPasswordFragment) {
        FRSCEventBusUtil.register(this);
        model = new EditUserPasswordModel();
        this.editeUserPasswordByOldPasswordFragment = editeUserPasswordByOldPasswordFragment;
    }

    public EditUserPasswordPresenter(EditeUserPasswordByPhoneNumberFragment editeUserPasswordByPhoneNumberFragment) {
        FRSCEventBusUtil.register(this);
        model = new EditUserPasswordModel();
        this.editeUserPasswordByPhoneNumberFragment = editeUserPasswordByPhoneNumberFragment;
    }

    public void modifyUserPasswordByPhoneNumber(User user) {
        editeUserPasswordByPhoneNumberFragment.showLoadingDialog();
        model.modifyUserPasswordByPhoneNumber(user, this);
    }

 public void modifyUserPasswordByOldPassword(User user) {
     editeUserPasswordByOldPasswordFragment.showLoadingDialog();
        model.modifyUserPasswordByOldPassword(user, this);
    }


    @Override
    public void modifyUserPasswordByPhoneResult(RequestResult requestResult) {
        RequestMessage message = new RequestMessage(requestResult, MessageCode.MODIFY_PASSWD_BY_PHONE_NUMBER_RESULT);
        FRSCEventBusUtil.sendMessage(message);
    }

    @Override
    public void modifyUserPasswordByOldPasswordResult(RequestResult requestResult) {
        RequestMessage message = new RequestMessage(requestResult, MessageCode.MODIFY_PASSWD_BY_OLD_PASSWORD_RESULT);
        FRSCEventBusUtil.sendMessage(message);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMessage(RequestMessage message) {
        if (message.getMessageCode() == MessageCode.MODIFY_PASSWD_BY_PHONE_NUMBER_RESULT) {
            editeUserPasswordByPhoneNumberFragment.hideLoadingDialog();
            if (message.isSuccess()){
                XToastUtils.success("修改成功");
                editeUserPasswordByPhoneNumberFragment.clearDataInput();
            }else {
                XToastUtils.error(message.getMessage());
            }
        }else if(message.getMessageCode() == MessageCode.MODIFY_PASSWD_BY_OLD_PASSWORD_RESULT) {
            editeUserPasswordByOldPasswordFragment.hideLoadingDialog();
            if (message.isSuccess()){
                XToastUtils.success("修改成功");
                editeUserPasswordByOldPasswordFragment.clearDataInput();
            }else {
                XToastUtils.error(message.getMessage());
            }
        }

    }


}
