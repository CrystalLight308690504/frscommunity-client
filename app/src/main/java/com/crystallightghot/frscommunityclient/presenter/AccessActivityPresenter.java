package com.crystallightghot.frscommunityclient.presenter;

import com.crystallightghot.frscommunityclient.model.UserModel;
import com.crystallightghot.frscommunityclient.view.activity.AccessActivity;
import com.crystallightghot.frscommunityclient.view.message.RequestMessage;
import com.crystallightghot.frscommunityclient.view.pojo.blog.DaoSession;
import com.crystallightghot.frscommunityclient.view.pojo.system.*;
import com.crystallightghot.frscommunityclient.view.util.FRSCApplicationContext;
import com.crystallightghot.frscommunityclient.view.util.FRSCDataBaseUtil;
import com.crystallightghot.frscommunityclient.view.util.FRSCEventBusUtil;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @Date 2022/2/26
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class AccessActivityPresenter {

    private AccessActivity view;
    User user;
    UserModel userModel;
    RespondMessageKey isLogin = new RespondMessageKey();

    public AccessActivityPresenter(AccessActivity view) {
        userModel = new UserModel();
        this.view = view;
        FRSCEventBusUtil.register(this);
    }

    public void checkUserLoginState() {
        DaoSession daoSession = FRSCDataBaseUtil.getReadDaoSession();
        LoginInformationDao informationDao = daoSession.getLoginInformationDao();
        LoginInformation information = informationDao.queryBuilder()
                .where(LoginInformationDao.Properties.Login.eq(1))
                .build()
                .unique();
        // 如果存在已经登陆的用户
        if (information != null) {
            UserDao userDao = daoSession.getUserDao();
            user = userDao.queryBuilder()
                    .where(UserDao.Properties.UserId.eq(information.getUserId()))
                    .build()
                    .unique();
            // 检查是否登陆失效
            userModel.isLogin(user.getSessionId(), isLogin);
        }else {
            view.stateToLogin(false);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMessage(RequestMessage message) {
        /**
         * 不添加下面的代码，会重复请求 导致回退异常
         */
        if (message.getMessageKey() != isLogin) {
            return;
        }
        if (message.isSuccess()) {
            if ((boolean) message.getData()) {
                DaoSession daoSession = FRSCDataBaseUtil.getReadDaoSession();
                RoleDao roleDao = daoSession.getRoleDao();
                Role role = roleDao.queryBuilder()
                        .where(RoleDao.Properties.UserId.eq(user.getUserId()))
                        .build()
                        .unique();
                user.setRole(role);
                FRSCApplicationContext.setUser(user);
                // 转化为登陆状态
                view.stateToLogin(true);
            }else {
                view.stateToLogin(false);
            }
        }
    }

    class RespondMessageKey{}
}
