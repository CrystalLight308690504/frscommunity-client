package com.crystallightghot.frscommunityclient.presenter;

import com.crystallightghot.frscommunityclient.contract.EditUserGenderContract;
import com.crystallightghot.frscommunityclient.contract.RequestCallBack;
import com.crystallightghot.frscommunityclient.model.EditUserGenderModel;
import com.crystallightghot.frscommunityclient.view.enums.FRSCRequestIOE;
import com.crystallightghot.frscommunityclient.view.fragment.EditeUserInformationFragment;
import com.crystallightghot.frscommunityclient.view.message.RequestMessage;
import com.crystallightghot.frscommunityclient.view.message.UserChangedMessage;
import com.crystallightghot.frscommunityclient.view.pojo.system.RequestResult;
import com.crystallightghot.frscommunityclient.view.pojo.system.User;
import com.crystallightghot.frscommunityclient.view.pojo.system.UserDao;
import com.crystallightghot.frscommunityclient.view.util.FRSCApplicationContext;
import com.crystallightghot.frscommunityclient.view.util.FRSCDataBaseUtil;
import com.crystallightghot.frscommunityclient.view.util.FRSCEventBusUtil;
import com.crystallightghot.frscommunityclient.view.util.XToastUtils;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @Date 2022/1/30
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class EditUserGenderPresenter implements EditUserGenderContract.Presenter , RequestCallBack {
    EditeUserInformationFragment view;
    EditUserGenderModel model;
    User user;

    public EditUserGenderPresenter(EditeUserInformationFragment view) {
        this.view = view;
        model = new EditUserGenderModel();
        user = FRSCApplicationContext.getUser();
        FRSCEventBusUtil.register(this);
    }
    public void  modifyUserGender(String gender) {
        view.showLoadingDialog();
        user.setGender(gender);
        model.modifyUserGender(user,this);
    }


    @Override
    public void callBack(RequestResult requestResult) {
        RequestMessage message = new RequestMessage(requestResult, FRSCRequestIOE.MODIFY_USER_GENDER);
        FRSCEventBusUtil.sendMessage(message);
    }

    /**
     * @param message
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMessage(RequestMessage message) {
        view.hideLoadingDialog();
        if (message.getRequestIO() != FRSCRequestIOE.MODIFY_USER_GENDER) {
            return;
        }
        if (message.isSuccess()) {
            XToastUtils.success("修改成功");

            // 修改保存在本地数据库的user
            UserDao userDao = FRSCDataBaseUtil.getWriteDaoSession().getUserDao();
            userDao.save(user);
            // 发送修改用户事件
            UserChangedMessage userChangedMessage = new UserChangedMessage();
            FRSCEventBusUtil.sendMessage(userChangedMessage);
        } else {
            XToastUtils.error(message.getMessage());
        }
    }
}
