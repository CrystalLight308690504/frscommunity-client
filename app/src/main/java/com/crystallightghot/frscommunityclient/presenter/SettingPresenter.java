package com.crystallightghot.frscommunityclient.presenter;

import com.crystallightghot.frscommunityclient.contract.RequestCallBack;
import com.crystallightghot.frscommunityclient.contract.SettingContract;
import com.crystallightghot.frscommunityclient.model.SettingModel;
import com.crystallightghot.frscommunityclient.view.message.UnLoginMessage;
import com.crystallightghot.frscommunityclient.view.pojo.system.*;
import com.crystallightghot.frscommunityclient.view.util.FRSCApplicationContext;
import com.crystallightghot.frscommunityclient.view.util.FRSCDataBaseUtil;
import com.crystallightghot.frscommunityclient.view.util.FRSCEventBusUtil;

/**
 * @Date 2022/1/25
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class SettingPresenter implements SettingContract.Presenter, RequestCallBack {
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
        model.unLoginUser(user, this);
    }


    @Override
    public void callBack(RequestResult requestResult) {
        view.hideLoadingDialog();
        // 将登陆状态信息删除
        User user = FRSCApplicationContext.getUser();
        DaoSession daoSession = FRSCDataBaseUtil.getWriteDaoSession();
        LoginInformationDao informationDao = daoSession.getLoginInformationDao();
        LoginInformation loginInformation = informationDao.queryBuilder()
                .where(LoginInformationDao.Properties.UserId.eq(user.getUserId()))
                .build().unique();
        daoSession.delete(loginInformation);
        // 删除用户
        UserDao userDao = daoSession.getUserDao();
        User user1 = userDao.queryBuilder()
                .where(UserDao.Properties.UserId.eq(user.getUserId()))
                .build().unique();
        if (null != user1) {
            userDao.delete(user1);
        }

        UnLoginMessage message = new UnLoginMessage();
        message.setMessage(requestResult.getMessage());
        message.setSuccess(true);
        FRSCEventBusUtil.sendMessage(message);

    }
}