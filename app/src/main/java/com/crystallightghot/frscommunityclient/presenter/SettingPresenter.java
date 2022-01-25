package com.crystallightghot.frscommunityclient.presenter;

import com.crystallightghot.frscommunityclient.contract.RespondCallBck;
import com.crystallightghot.frscommunityclient.contract.SettingContract;
import com.crystallightghot.frscommunityclient.model.SettingModel;
import com.crystallightghot.frscommunityclient.utils.EventBusUtil;
import com.crystallightghot.frscommunityclient.utils.FRSCApplicationContext;
import com.crystallightghot.frscommunityclient.view.message.UnLoginMessage;
import com.crystallightghot.frscommunityclient.view.pojo.system.DaoSession;
import com.crystallightghot.frscommunityclient.view.pojo.system.LoginInformation;
import com.crystallightghot.frscommunityclient.view.pojo.system.LoginInformationDao;
import com.crystallightghot.frscommunityclient.view.pojo.system.User;
import com.crystallightghot.frscommunityclient.view.util.FRSCDataBaseUtil;

/**
 * @Date 2022/1/25
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class SettingPresenter implements SettingContract.Presenter , RespondCallBck {
    private final SettingContract.View view;
    private final SettingModel model;
    private final User user;

    public SettingPresenter(SettingContract.View view, User user) {
        this.view = view;
        model = new SettingModel();
        this.user = user;
    }

    public void unLogin() {
        view.showLoadingDialog();
        model.unLoginUser(user,this);
    }

    @Override
    public void success(String respondMessage, Object respondData) {

        // 将登陆状态信息删除
        User user = FRSCApplicationContext.getUser();
        DaoSession daoSession = FRSCDataBaseUtil.getWriteDaoSession();
        LoginInformationDao informationDao = daoSession.getLoginInformationDao();
        LoginInformation loginInformation = informationDao.queryBuilder()
                .where(LoginInformationDao.Properties.UserId.eq(user.getUserId()))
                .build().unique();
        daoSession.delete(loginInformation);

        UnLoginMessage message = new UnLoginMessage();
        message.setMessage(respondMessage);
        message.setSuccess(true);
        EventBusUtil.sendMessage(message);

        view.hideLoadingDialog();


    }

    @Override
    public void failure(String failureMessage) {
        UnLoginMessage message = new UnLoginMessage();
        message.setMessage(failureMessage);
        message.setSuccess(false);
        EventBusUtil.sendMessage(message);
        view.hideLoadingDialog();
    }
}
