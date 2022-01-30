package com.crystallightghot.frscommunityclient.presenter;


import com.crystallightghot.frscommunityclient.contract.LoginContract;
import com.crystallightghot.frscommunityclient.contract.RequestCallBack;
import com.crystallightghot.frscommunityclient.model.LoginModel;
import com.crystallightghot.frscommunityclient.view.util.FRSCApplicationContext;
import com.crystallightghot.frscommunityclient.view.enums.MessageCode;
import com.crystallightghot.frscommunityclient.view.message.RequestMessage;
import com.crystallightghot.frscommunityclient.view.pojo.system.*;
import com.crystallightghot.frscommunityclient.view.util.FRSCDataBaseUtil;
import com.google.gson.Gson;
import org.greenrobot.eventbus.EventBus;

/**
 * @Date 2022/1/21
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class LoginPresenter implements LoginContract.Presenter, RequestCallBack {

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
    public void login(User user) {
        view.showLoadingDialog();
        Gson gson = new Gson();
        String userJson = gson.toJson(user);
        model.login(userJson, this);
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
            User user = userDao.queryBuilder()
                    .where(UserDao.Properties.UserId.eq(information.getUserId()))
                    .build()
                    .unique();

            FRSCApplicationContext.setUser(user);
            // 转化为登陆状态
            view.stateToLogin();
        }
    }

    @Override
    public void callBack(RequestResult requestResult) {
        if (requestResult.isSuccess()) {
            view.hideLoadingDialog();
            // 将获取的数据转化为User实体类
            Gson gson = new Gson();
            User user = gson.fromJson(gson.toJson(requestResult.getData()), User.class);

            // 创建消息
            RequestMessage<User> message = new RequestMessage();
            message.setSuccess(true);
            message.setMessage(requestResult.getMessage());
            message.setData(user);
            message.setMessageCode(MessageCode.LOGIN_RESULT);

            // 存储User信息到全局中
            FRSCApplicationContext.setUser(user);

            DaoSession daoSession = FRSCDataBaseUtil.getWriteDaoSession();
            UserDao userDao = daoSession.getUserDao();

            User userQuery = userDao.queryBuilder()
                    .where(UserDao.Properties.UserId.eq(user.getUserId()))
                    .build()
                    .unique();
            if (null == userQuery) {
                userDao.insert(user);
            }
            // 记录当前用户登陆状态
            LoginInformationDao loginInformationDao = daoSession.getLoginInformationDao();
            LoginInformation loginInformation = new LoginInformation(null,user.getUserId(),1);
            loginInformationDao.insert(loginInformation);

            EventBus.getDefault().post(message);

        }else {
            view.hideLoadingDialog();
            RequestMessage message = new RequestMessage();
            message.setMessage(requestResult.getMessage());
            message.setMessageCode(MessageCode.LOGIN_RESULT);
            message.setSuccess(false);
            EventBus.getDefault().post(message);
        }

    }
}
